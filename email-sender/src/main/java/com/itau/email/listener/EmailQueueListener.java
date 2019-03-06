package com.itau.email.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.itau.email.dto.EmailDTO;
import com.itau.email.emailcomponent.EmailSenderComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luis
 */
@Slf4j
@Component
public class EmailQueueListener {

	@Autowired private EmailSenderComponent emailSenderComponent;

	/**
	 * Method responsible for listening and receiving the messages from the sending queue of emails and start sending the email to the corresponding message.
	 * @param emailDTO contains the e-mail informations.
	 */
	@JmsListener(destination = "itau.emailsender.queue")
	public void onReceiverQueue(final EmailDTO emailDTO) {
		if (validEmailDTO(emailDTO)) {
			try {
				emailSenderComponent.sendEmailMessage(emailDTO);
			} catch (final Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		} else {
			log.error("FAILURE TO VALID THE MANDATORY PARAMETERS TO SEND EMAIL. CLIENTE EMAIL: " + emailDTO.getClientEmail() + ", EMAIL TEMPLATE: " + emailDTO.getEmailTemplate());
		}
	}

	/**
	 * Method responsible for validating if the minimum parameters for sending e-mail were sent correctly.
	 * @param emailDTO contains the e-mail informations.
	 * @return true if parameters are Ok.
	 */
	private static boolean validEmailDTO(final EmailDTO emailDTO) {
		boolean ret = true;
		if (emailDTO.getClientEmail() == null || StringUtils.isEmpty(emailDTO.getClientEmail().trim()) || //
				emailDTO.getEmailTemplate() == null || StringUtils.isEmpty(emailDTO.getEmailTemplate().trim())) {
			ret = false;
		}
		return ret;
	}
}