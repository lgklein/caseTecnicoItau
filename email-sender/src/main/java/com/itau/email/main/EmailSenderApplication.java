package com.itau.email.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Spring Boot Starter App
 * @author luis
 */
@SpringBootApplication(scanBasePackages = { "com.itau.email" })
@EnableJms
public class EmailSenderApplication {

	public static void main(final String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
	}
}