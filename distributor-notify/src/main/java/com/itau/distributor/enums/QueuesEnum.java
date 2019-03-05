package com.itau.distributor.enums;

import lombok.Getter;

/**
 * @author luis
 */
public enum QueuesEnum {
	EMAIL("itau.emailsender.queue"), SMS("itau.smssender.queue");

	@Getter private String queueAdress;

	private QueuesEnum(final String queueAdress) {
		this.queueAdress = queueAdress;
	}
}