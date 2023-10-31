package com.microadministration.model;

import jakarta.persistence.*;
import lombok.Data;
import com.microadministration.dto.BillDTO;
import com.microadministration.dto.NewBillDTO;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "factura")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="factura_id")
	private long billId;

	@Column(name="fecha_factura")
	private Timestamp billDate;
	@Column(name="monto")
	private Double amount;
	@Column(name="descripcion")
	private String description;

	public Bill(){
		super();
	}

	public Bill(Timestamp billDate, Double amount, String description) {
		this.billDate = billDate;
		this.amount = amount;
		this.description = description;
	}

	public Bill(BillDTO dto){
		this.billDate = dto.getBillDate();
		this.amount = dto.getAmount();
		this.description = dto.getDescription();
	}

	public Bill(NewBillDTO dto){
		this.billDate = dto.getBillDate();
		this.amount = dto.getAmount();
		this.description = dto.getDescription();
	}
}