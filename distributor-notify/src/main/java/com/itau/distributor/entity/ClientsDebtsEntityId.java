package com.itau.distributor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author luis
 */
@Embeddable
public class ClientsDebtsEntityId implements Serializable {

	private static final long serialVersionUID = 8460326668855831357L;
	@Getter @Setter @Column(name = "ID_CLIENT", nullable = false, precision = 9, scale = 0) private int idClient;
	@Getter @Setter @Column(name = "ID_DEBIT", nullable = false, precision = 9, scale = 0) private int idDebit;

	public ClientsDebtsEntityId() {
	}

	public ClientsDebtsEntityId(final int idClient, final int idDebit) {
		this.idClient = idClient;
		this.idDebit = idDebit;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof ClientsDebtsEntityId)) {
			return false;
		}
		final ClientsDebtsEntityId castOther = (ClientsDebtsEntityId) other;
		return getIdClient() == castOther.getIdClient() && getIdDebit() == castOther.getIdDebit();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + getIdClient();
		result = 37 * result + getIdDebit();
		return result;
	}
}