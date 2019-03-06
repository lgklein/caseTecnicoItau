package com.itau.sms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.itau.sms.component.SmsSenderComponent;
import com.itau.sms.dto.SmsDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luis
 */
@Slf4j
@Component
public class SmsQueueListener {

	@Autowired private SmsSenderComponent smsSenderComponent;

	/**
	 * Method responsible for listening and receiving the messages from the sending queue of SMS and start sending the SMS to the corresponding message.
	 * @param SmsDTO contains the SMS informations.
	 */
	@JmsListener(destination = "itau.smssender.queue")
	public void onReceiverQueue(final SmsDTO smsDTO) {
		if (validSmsDTO(smsDTO)) {
			try {
				smsSenderComponent.sendSms(smsDTO);
			} catch (final Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		} else {
			log.error("FAILURE TO VALID THE MANDATORY PARAMETERS TO SEND SMS. CLIENTE PHONE NUMBER: " + smsDTO.getClientPhoneNumber() + ", MESSAGE: " + smsDTO.getMessage());
		}
	}

	/**
	 * Method responsible for validating if the minimum parameters for sending SMS were sent correctly.
	 * @param SmsDTO contains the SMS informations.
	 * @return true if parameters are Ok.
	 */
	private static boolean validSmsDTO(final SmsDTO smsDTO) {
		boolean ret = true;
		if (smsDTO.getClientPhoneNumber() == null || StringUtils.isEmpty(smsDTO.getClientPhoneNumber().trim()) || //
				smsDTO.getMessage() == null || StringUtils.isEmpty(smsDTO.getMessage().trim())) {
			ret = false;
		}
		return ret;
	}
}