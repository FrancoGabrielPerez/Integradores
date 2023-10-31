package com.microadministration.dto;

import lombok.Getter;

import lombok.RequiredArgsConstructor;

import com.microadministration.model.Bill;

import java.sql.Timestamp;


@Getter
@RequiredArgsConstructor
public class BillDTO {
	private long billId;
	private Timestamp billDate;
	private Double amount;
	private String description;

	public BillDTO(long billId, Timestamp billDate, Double amount, String description) {
		this.billDate = billDate;
		this.amount = amount;
		this.description = description;
		this.billId = billId;
	}

	public BillDTO(Bill bill) {
		this.billId = bill.getBillId();
		this.billDate = bill.getBillDate();
		this.amount = bill.getAmount();
		this.description = bill.getDescription();
	}
}