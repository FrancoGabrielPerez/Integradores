package com.microsecurity.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microsecurity.dto.AdminStaffDTO;
import com.microsecurity.model.AdminStaff;
import com.microsecurity.repository.AdminStaffRepository;
import com.microsecurity.repository.AuthorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microsecurity.exception.EnumUserException;
import com.microsecurity.exception.NotFoundException;
import com.microsecurity.exception.UserException;

import java.util.stream.Collectors;

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
	
	@Autowired	
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityRepository authorityRepository;
	
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
	/* @Transactional
	public AdminStaffDTO save(AdminStaffDTO entity) {
		return new AdminStaffDTO(this.adminStaffRepository.save(new AdminStaff(entity)));
	} */

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
		adminStaffRepository.save(admin);
	}

	/**
	 * findByRol
	 * @param rol
	 * @return List<AdminStaffDTO>
	 */
	// public List<AdminStaffDTO> findByRol(String rol) {
	// 	return this.adminStaffRepository.findByRol(rol).stream().map(AdminStaffDTO::new).toList();
	// }

	/**
	 * createUser
	 * Crea un usuario en la base de datos.
	 * @param request
	 * @return AdminStaffDTO
	 */
	@Transactional
	public AdminStaffDTO createUser(AdminStaffDTO request ) {
		if( this.adminStaffRepository.existsUsersByEmailIgnoreCase( request.getEmail() ) )
			throw new UserException(EnumUserException.already_exist, String.format("Ya existe un usuario con email %s", request.getEmail()));	
		final var authorities = request.getAuthorities()
				.stream()
				.map( string -> this.authorityRepository.findById( string ).orElseThrow( () -> new NotFoundException("Authority", string ) ) )
				.collect(Collectors.toList());
		if( authorities.isEmpty() )
			throw new IllegalArgumentException("No se encontro ninguna autoridad con id " + request.getAuthorities().toString() );
		final var encryptedPassword = passwordEncoder.encode(request.getPassword());
		final var user = new AdminStaff( request );
		user.setPassword( encryptedPassword );
		user.setAuthorities( authorities );
		final var createdUser = this.adminStaffRepository.save( user );
		return new AdminStaffDTO( createdUser );
	}
}
       