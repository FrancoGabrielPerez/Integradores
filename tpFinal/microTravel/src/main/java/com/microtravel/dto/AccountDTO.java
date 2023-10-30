package com.microtravel.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AccountDTO {
    private long accountId;
    private Timestamp fechaAlta;
	private boolean habilitada;
	private String idMPago;
	private Double saldo;

	public AccountDTO() {}

	public AccountDTO(long accountId, Timestamp fechaAlta, boolean habilitada, String idMPago, Double saldo) {
        this.accountId = accountId;
		this.fechaAlta = fechaAlta;
		this.habilitada = habilitada;
		this.saldo = saldo;
		this.idMPago = idMPago;
	}
}
