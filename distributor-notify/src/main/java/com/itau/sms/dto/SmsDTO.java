package com.itau.sms.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO responsible for carrying the standard information for sending SMS.
 * @author luis
 */
public class SmsDTO implements Serializable {

	private static final long serialVersionUID = 5385765190594370569L;
	@Getter @Setter private String clientPhoneNumber;
	@Getter @Setter private String message;
}