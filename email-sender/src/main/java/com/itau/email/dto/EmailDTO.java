package com.itau.email.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO responsible for carrying the standard information for sending E-mail.
 * @author luis
 */
public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 670569502906716112L;
	@Getter @Setter private String clientEmail;
	@Getter @Setter private String emailTemplate;
	@Getter private final Map<String, String> params = new HashMap<>();
}