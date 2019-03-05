package com.itau.distributor.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

/**
 * Spring Boot Starter App
 * @author luis
 */
@SpringBootApplication(scanBasePackages = { "com.itau.distributor" })
@EnableJpaRepositories("com.itau.distributor.repository")
@EntityScan("com.itau.distributor.entity")
@EnableJms
public class DistributorApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DistributorApplication.class, args);
	}
}