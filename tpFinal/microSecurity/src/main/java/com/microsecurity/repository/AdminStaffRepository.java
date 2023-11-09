package com.microsecurity.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microsecurity.model.AdminStaff;

/**
 * AdminStaffRepository
 * 
 * Repositorio de integrantes del staff de administracion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Repository ("adminStaffRepository")
public interface AdminStaffRepository extends JpaRepository<AdminStaff, Long> {

    /**
     * Busca todos los integrantes del staff de administracion por su rol.
     * @param rol
     * @return Collection<AdminStaff>
     */
    // @Query("SELECT a FROM AdminStaff a WHERE a.rol = :rol")
    // Collection<AdminStaff> findByRol(String rol);

    Optional<AdminStaff> findUserByEmailIgnoreCase(String email);

    boolean existsUsersByEmailIgnoreCase(String email );
}

