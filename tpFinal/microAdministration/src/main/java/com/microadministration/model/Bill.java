package com.microadministration.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.dto.BillDTO;

import java.security.Timestamp;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "factura")
public class Bill {
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

	public Bill(){
		super();
	}

	public Bill(Timestamp billDate, String amount, String description) {
		this.billDate = billDate;
		this.amount = amount;
		this.description = description;
	}

	public Bill(BillDTO dto){
		this.billDate = dto.getBillDate();
		this.amount = dto.getAmount();
		this.description = dto.getDescription();
	}
}