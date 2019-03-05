package com.itau.distributor.service;

import java.util.concurrent.Callable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;

import com.itau.distributor.entity.ClientsDebtsEntity;
import com.itau.distributor.enums.QueuesEnum;
import com.itau.distributor.enums.TemplatesEnum;
import com.itau.distributor.repository.ClientsDebtsRepository;
import com.itau.distributor.utils.FormatterUtils;
import com.itau.email.dto.EmailDTO;
import com.itau.sms.dto.SmsDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luis
 */
@Slf4j
public class DistributorServiceThread implements Callable<Boolean> {

	public static int AMOUNT_PER_PAGE = 100000;
	private final ClientsDebtsRepository clientsDebtsRepository;
	private final JmsTemplate jmsTemplate;
	private final int page;

	public DistributorServiceThread(final ClientsDebtsRepository clientsDebtsRepository, final JmsTemplate jmsTemplate, final int page) {
		this.clientsDebtsRepository = clientsDebtsRepository;
		this.jmsTemplate = jmsTemplate;
		this.page = page;
	}

	@Override
	public Boolean call() {
		try {
			final Pageable pageable = PageRequest.of(page, AMOUNT_PER_PAGE, Sort.by("clientName").ascending());
			final Iterable<ClientsDebtsEntity> clientsDebits = clientsDebtsRepository.findAll(pageable);
			clientsDebits.forEach(clientDebit -> sendToQueues(clientDebit));
		} catch (final Exception exception) {
			log.info("ERROR ON THREAD FROM PAGE = " + page, exception);
		}
		return true;
	}

	/**
	 * Method responsible for, based on the sending data of the client notifications,
	 * send the notification request to the corresponding queue.
	 * @param clientDebit Information of the debts and customer contacts.
	 */
	private void sendToQueues(final ClientsDebtsEntity clientDebit) {
		if (clientDebit.isSendEmail()) {
			jmsTemplate.convertAndSend(QueuesEnum.EMAIL.getQueueAdress(), getEmailDTO(clientDebit));
		}
		if (clientDebit.isSendSms()) {
			jmsTemplate.convertAndSend(QueuesEnum.SMS.getQueueAdress(), getSmsDTO(clientDebit));
		}
	}

	/**
	 * Method responsible for mounting the standard DTO sending E-mail based on the information of the debts and customer contact.
	 * @param clientDebit Information of the debts and customer contacts.
	 * @return EmailDTO Contains all the client's and e-mail informations.
	 */
	private EmailDTO getEmailDTO(final ClientsDebtsEntity clientDebit) {
		final EmailDTO emailDTO = new EmailDTO();
		emailDTO.setClientEmail(clientDebit.getClientEmail());
		emailDTO.setEmailTemplate(TemplatesEnum.EMAIL_DEBIT_NOTIFY.getTemplate());
		emailDTO.getParams().put("clientName", clientDebit.getClientName());
		emailDTO.getParams().put("debitDescription", clientDebit.getDebitDescription());
		emailDTO.getParams().put("debitValue", FormatterUtils.formatterBigDecimalToMonetaryString(clientDebit.getDebitValue()));
		return emailDTO;
	}

	/**
	 * Method responsible for mounting the standard DTO sending SMS based on the information of the debts and customer contact.
	 * @param clientDebit Information of the debts and customer contacts.
	 * @return SmsDTO Contains the client's name and mobile number and the message to be sent in the SMS.
	 */
	private SmsDTO getSmsDTO(final ClientsDebtsEntity clientDebit) {
		final SmsDTO emailDTO = new SmsDTO();
		emailDTO.setClientName(clientDebit.getClientName());
		emailDTO.setClientPhoneNumber(clientDebit.getClientPhoneNumber());
		emailDTO.setMessage(clientDebit.getDebitDescription());
		return emailDTO;
	}
}