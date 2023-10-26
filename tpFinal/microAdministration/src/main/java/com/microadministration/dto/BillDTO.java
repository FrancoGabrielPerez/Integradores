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

@Entity
@Getter
@Table(name = "factura")
public class BillDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id")
	private long adminId;

	@Column(name="fecha_factura")
	private Timestamp billDate;
	@Column(name="monto")
	private String amount;
	@Column(name="descipcion")
	private String description;

	public BillDTO(){
		super();
	}

	public BillDTO(Timestamp billDate, String amount, String description) {
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