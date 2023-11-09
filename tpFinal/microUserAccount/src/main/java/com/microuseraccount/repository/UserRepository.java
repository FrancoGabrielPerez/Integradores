package com.microuseraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microuseraccount.model.User;

/**
 * UserRepository
 * 
 * Repositorio de la entidad User.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Repository ("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}

