package com.itau.sms.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter App
 * @author luis
 */
@SpringBootApplication(scanBasePackages = { "com.itau.sms" })
public class SmsSenderApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SmsSenderApplication.class, args);
	}
}