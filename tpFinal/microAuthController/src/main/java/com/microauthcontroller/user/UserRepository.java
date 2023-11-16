package com.microauthcontroller.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 * Repositorio de usuarios.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}
