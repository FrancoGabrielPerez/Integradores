package com.microuseraccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microuseraccount.dto.AccountDTO;
import com.microuseraccount.model.Account;

/**
 * AccountRepository
 * 
 * Repositorio de la entidad Account.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Repository ("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("SELECT NEW com.microuseraccount.dto.AccountDTO(a) FROM Account a JOIN a.usuarios ua JOIN ua.user u WHERE u.userId = ?1")
	public List<AccountDTO> findByUserId(Long id);
}

