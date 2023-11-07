package com.microadministration.dto;

import lombok.Getter;

import lombok.RequiredArgsConstructor;

import com.microadministration.model.Bill;

import java.sql.Timestamp;

/**
 * BillDTO
 * 
 * DTO que contiene los atributos de una factura.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
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

	public NewBillDTO(Bill bill) {
		this.billDate = bill.getBillDate();
		this.amount = bill.getAmount();
		this.description = bill.getDescription();
	}
}