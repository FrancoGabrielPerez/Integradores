package com.microtravel.dto;

import lombok.Getter;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;


/**
 * NewBillDTO
 * 
 * DTO que contiene los atributos de una nueva factura.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Getter
@RequiredArgsConstructor
public class NewBillDTO {
	private Timestamp billDate;
	private Double amount;
	private String description;

	public NewBillDTO(Timestamp billDate, Double amount, String description) {
		this.billDate = billDate;
		this.amount = amount;
		this.description = description;
	}
}