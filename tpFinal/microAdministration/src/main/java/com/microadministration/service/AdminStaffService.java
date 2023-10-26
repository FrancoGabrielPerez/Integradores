package com.microadministration.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.AdminStaff;
import com.microadministration.repository.AdminStaffRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service("adminStaffService")
public class AdminStaffService{
	@Autowired
	private AdminStaffRepository adminStaffRepository;
		
	@Transactional(readOnly = true)
	public List<AdminStaffDTO> findAll() {
		return this.adminStaffRepository.findAll().stream().map(AdminStaffDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public AdminStaffDTO findById(Long id) {
		return this.adminStaffRepository.findById(id).map(AdminStaffDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
	}
	
	@Transactional
	public AdminStaffDTO save(AdminStaffDTO entity) {
		return new AdminStaffDTO(this.adminStaffRepository.save(new AdminStaff(entity)));
	}

	@Transactional
	public void delete(Long id) {
		adminStaffRepository.delete(adminStaffRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id)));
	}

	@Transactional
	public void update(Long id, AdminStaffDTO entity) {
		AdminStaff admin = adminStaffRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
		admin.setNombre(entity.getNombre());
		admin.setApellido(entity.getApellido());
		admin.setNroCelular(entity.getNroCelular());
		admin.setEmail(entity.getEmail());
		admin.setRol(entity.getRol());
		adminStaffRepository.save(admin);
	}

	public List<AdminStaffDTO> findByRol(String rol) {
		return this.adminStaffRepository.findByRol(rol).stream().map(AdminStaffDTO::new).toList();
	}
	
}
