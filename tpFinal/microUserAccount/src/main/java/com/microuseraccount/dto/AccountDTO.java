package com.microuseraccount.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

import com.microuseraccount.model.Account;

@Getter
@RequiredArgsConstructor
public class AccountDTO {
    private Timestamp fechaAlta;
	private boolean habilitada;
	private String idMPago;

	public AccountDTO(Account account) {
		this.fechaAlta = account.getFechaAlta();
		this.habilitada = account.isHabilitada();
		this.idMPago = account.getIdMPago();
	}

	public AccountDTO(Timestamp fechaAlta, boolean habilitada, String idMPago) {
		this.fechaAlta = fechaAlta;
		this.habilitada = habilitada;
		this.idMPago = idMPago;
	}
}