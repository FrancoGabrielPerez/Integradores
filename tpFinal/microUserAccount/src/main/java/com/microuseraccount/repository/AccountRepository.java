package com.microuseraccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microuseraccount.model.Account;
import com.microuseraccount.model.User;

@Repository ("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
}

