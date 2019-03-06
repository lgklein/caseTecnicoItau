package com.itau.sms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itau.sms.configuration.SmsSenderConfigurations;
import com.itau.sms.dto.SmsDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * @author luis
 */
@Component
public class SmsSenderComponent {

	@Autowired private SmsSenderConfigurations smsSenderConfigurations;
	private boolean twilioInitiated = false;

	/**
	 * Method responsible for assembling and sending the email.
	 * @param smsDTO contains the SMS informations.
	 */
	public void sendSms(final SmsDTO smsDTO) {
		startTwilio();
		//TODO: Create template for SMS message
		Message.creator(new PhoneNumber(smsDTO.getClientPhoneNumber()), new PhoneNumber("+55 11 97024-9215"), smsDTO.getMessage()).create();
	}

	/**
	 * Control method to start Twilio api.
	 */
	private void startTwilio() {
		if (!twilioInitiated) {
			Twilio.init(smsSenderConfigurations.getTwilioSid(), smsSenderConfigurations.getTwilioAuthId());
			twilioInitiated = true;
		}
	}
}