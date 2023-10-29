package com.microuseraccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microuseraccount.dto.AccountDTO;
import com.microuseraccount.model.Account;
import com.microuseraccount.model.User;

@Repository ("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	//List<Account> findByUsuarios(User user);
	// @Query("SELECT NEW com.microuseraccount.dto.AccountDTO(a) FROM Account a JOIN a.usuarios u WHERE u.userId = ?1")
	// public List<AccountDTO> findByUserId(Long id);

	@Query("SELECT NEW com.microuseraccount.dto.AccountDTO(a) FROM Account a JOIN a.usuarios ua JOIN ua.user u WHERE u.userId = ?1")
	public List<AccountDTO> findByUserId(Long id);
}

