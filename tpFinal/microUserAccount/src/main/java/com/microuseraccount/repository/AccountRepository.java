package com.microuseraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microuseraccount.model.Account;

@Repository ("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
}

