package com.microadministration.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.AdminStaff;
import com.microadministration.repository.AdminStaffRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * AdminStaffService
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Staff.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Service("adminStaffService")
public class AdminStaffService{
	@Autowired
	private AdminStaffRepository adminStaffRepository;
	
	/**
	 * findAll
	 * Devuelve una lista con todos los integrantes del staff.
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<AdminStaffDTO> findAll() {
		return this.adminStaffRepository.findAll().stream().map(AdminStaffDTO::new ).toList();
	}

	/**
	 * findById
	 * Devuelve un integrante del staff por su id.
	 * @param id
	 */
	@Transactional(readOnly = true)
	public AdminStaffDTO findById(Long id) {
		return this.adminStaffRepository.findById(id).map(AdminStaffDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
	}
	
	/**
	 * save
	 * Guarda un integrante del staff en la base de datos.
	 * @param entity
	 * @return
	 */
	@Transactional
	public AdminStaffDTO save(AdminStaffDTO entity) {
		return new AdminStaffDTO(this.adminStaffRepository.save(new AdminStaff(entity)));
	}

	/**
	 * delete
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		adminStaffRepository.delete(adminStaffRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id)));
	}

	/**
	 * update
	 * Actualiza los datos de un integrante del staff
	 * @param id
	 * @param entity
	 */
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

	/**
	 * findByRol
	 * @param rol
	 * @return List<AdminStaffDTO>
	 */
	public List<AdminStaffDTO> findByRol(String rol) {
		return this.adminStaffRepository.findByRol(rol).stream().map(AdminStaffDTO::new).toList();
	}
}
       