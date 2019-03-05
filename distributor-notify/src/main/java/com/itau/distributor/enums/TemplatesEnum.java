
package com.itau.distributor.enums;

import lombok.Getter;

/**
 * @author luis
 */
public enum TemplatesEnum {
	EMAIL_DEBIT_NOTIFY("templateDebitNotify.html");

	@Getter private String template;

	private TemplatesEnum(final String template) {
		this.template = template;
	}
}