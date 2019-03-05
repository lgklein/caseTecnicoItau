package com.itau.sms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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
		try {
			smsSenderComponent.sendSms(smsDTO);
		} catch (final Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}