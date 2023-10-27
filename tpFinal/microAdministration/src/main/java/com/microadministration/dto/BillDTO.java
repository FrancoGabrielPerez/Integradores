package com.microadministration.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.Bill;

import java.security.Timestamp;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Getter
@Table(name = "factura")
public class BillDTO {	
	private long adminId;
	private Timestamp billDate;
	private Double amount;
	private String description;

	public BillDTO(){
		super();
	}

	public BillDTO(Timestamp billDate, Double amount, String description) {
		this.billDate = billDate;
		this.amount = amount;
		this.description = description;
	}

	public BillDTO(Bill bill) {
		this.billDate = bill.getBillDate();
		this.amount = bill.getAmount();
		this.description = bill.getDescription();
	}
}