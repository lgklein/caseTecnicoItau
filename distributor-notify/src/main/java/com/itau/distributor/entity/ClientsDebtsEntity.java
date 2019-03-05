package com.itau.distributor.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author luis
 */
@Entity
@Table(name = "CLIENTS_DEBTS")
public class ClientsDebtsEntity implements Serializable {

	private static final long serialVersionUID = -107196916194489836L;
	@Getter @Setter @EmbeddedId @AttributeOverrides({ @AttributeOverride(name = "idClient", column = @Column(name = "ID_CLIENT", nullable = false, precision = 9, scale = 0)), //
			@AttributeOverride(name = "idDebit", column = @Column(name = "ID_DEBIT", nullable = false, precision = 9, scale = 0)) }) private ClientsDebtsEntityId id;
	@Getter @Setter @Column(name = "CLIENT_NAME", nullable = false, length = 200) private String clientName;
	@Getter @Setter @Column(name = "CLIENT_EMAIL", nullable = false, length = 200) private String clientEmail;
	@Getter @Setter @Column(name = "CLIENT_PHONE_NUMBER", nullable = false, length = 20) private String clientPhoneNumber;
	@Getter @Setter @Column(name = "DEBIT_DESCRIPTION", nullable = false, length = 200) private String debitDescription;
	@Getter @Setter @Column(name = "DEBIT_VALUE", nullable = false, precision = 11) private BigDecimal debitValue;
	@Getter @Setter @Column(name = "SEND_SMS", nullable = false) private boolean sendSms;
	@Getter @Setter @Column(name = "SEND_EMAIL", nullable = false) private boolean sendEmail;

	public ClientsDebtsEntity() {
	}

	public ClientsDebtsEntity(final ClientsDebtsEntityId id, final String clientName, final String clientEmail, final String clientPhoneNumber, final String debitDescription, //
			final BigDecimal debitValue, final boolean sendSms, final boolean sendEmail) {
		this.id = id;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientPhoneNumber = clientPhoneNumber;
		this.debitDescription = debitDescription;
		this.debitValue = debitValue;
		this.sendSms = sendSms;
		this.sendEmail = sendEmail;
	}
}