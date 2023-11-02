package com.microadministration.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microadministration.dto.BillDTO;
import com.microadministration.dto.NewBillDTO;
import com.microadministration.model.Bill;
import com.microadministration.repository.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service("billService")
public class BillService{
	@Autowired
	private BillRepository billRepository;
		
	@Transactional(readOnly = true)
	public List<BillDTO> findAll() {
		return this.billRepository.findAll().stream().map(BillDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public BillDTO findById(Long id) {
		return this.billRepository.findById(id).map(BillDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id));
	}
	
	@Transactional
	public BillDTO save(NewBillDTO entity) {
		return new BillDTO(this.billRepository.save(new Bill(entity)));
	}

	@Transactional
	public void delete(Long id) {
		billRepository.delete(billRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id)));
	}

	@Transactional
	public void update(Long id, BillDTO entity) {
		Bill bill = billRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id));
		bill.setBillDate(entity.getBillDate());
		bill.setAmount(entity.getAmount());
		bill.setDescription(entity.getDescription());
		billRepository.save(bill);
	}

	@Transactional(readOnly = true)
	public Double getFacturacion(String fechaDesde, String fechaHasta) {
		checkValidDateFormat(fechaDesde);
		checkValidDateFormat(fechaHasta);
		return this.billRepository.getFacturacion(fechaDesde, fechaHasta);
	}

	

	private void checkValidDateFormat(String fecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate.parse(fecha, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + fecha);
		}
	}
	
}
