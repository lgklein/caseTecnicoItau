package com.itau.email.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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
		try {
			emailSenderComponent.sendEmailMessage(emailDTO);
		} catch (final Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}