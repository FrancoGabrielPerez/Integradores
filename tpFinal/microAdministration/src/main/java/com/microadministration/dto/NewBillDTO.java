package com.microadministration.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.RequiredArgsConstructor;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.Bill;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


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