package com.itau.sms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

/**
 * @author luis
 */
@Configuration
public class SmsSenderConfigurations {

	@Getter @Value("${spring.twilio.sid}") private String twilioSid;
	@Getter @Value("${spring.twilio.authid}") private String twilioAuthId;
}