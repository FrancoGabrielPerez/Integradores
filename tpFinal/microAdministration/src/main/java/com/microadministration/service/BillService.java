package com.microadministration.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microadministration.dto.BillDTO;
import com.microadministration.model.AdminStaff;
import com.microadministration.model.Bill;
import com.microadministration.repository.AdminStaffRepository;
import com.microadministration.repository.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service("billService")
public class BillService{
	@Autowired
	private BillRepository billRepository;
		
	@Transactional(readOnly = true)
	public List<BillDTO> findAll() {
		return this.billRepository.findAll().stream().map(BillDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public BillDTO findById(Long id) {
		return this.billRepository.findById(id).map(BillDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
	}
	
	@Transactional
	public BillDTO save(BillDTO entity) {
		return new BillDTO(this.billRepository.save(new Bill(entity)));
	}

	@Transactional
	public void delete(Long id) {
		billRepository.delete(billRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id)));
	}

	@Transactional
	public void update(Long id, BillDTO entity) {
		Bill bill = billRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
		bill.setBillDate(entity.getBillDate());
		bill.setAmount(entity.getAmount());
		bill.setDescription(entity.getDescription());
		billRepository.save(bill);
	}

	@Transactional(readOnly = true)
	public Double getFacturacion(String fechaDesde, String fechaHasta) {
		return this.billRepository.getFacturacion(fechaDesde, fechaHasta);
	}
	
}
