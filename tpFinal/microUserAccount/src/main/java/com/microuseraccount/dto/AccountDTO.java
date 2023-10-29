package com.microuseraccount.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

import com.microuseraccount.model.Account;

@Getter
@RequiredArgsConstructor
public class AccountDTO {
	private long accountId;
    private Timestamp fechaAlta;
	private boolean habilitada;
	private String idMPago;
	private Double saldo;

	public AccountDTO(Account account) {
		this.accountId = account.getAccountId();
		this.fechaAlta = account.getFechaAlta();
		this.habilitada = account.isHabilitada();
		this.saldo = account.getSaldo();
		this.idMPago = account.getIdMPago();
	}

	public AccountDTO(Timestamp fechaAlta, boolean habilitada, String idMPago, Double saldo) {
		this.fechaAlta = fechaAlta;
		this.habilitada = habilitada;
		this.idMPago = idMPago;
		this.saldo = saldo;
	}
}