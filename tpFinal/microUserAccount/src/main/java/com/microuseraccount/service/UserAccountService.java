package com.microuseraccount.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microuseraccount.dto.AccountDTO;
import com.microuseraccount.model.Account;
import com.microuseraccount.model.User;
import com.microuseraccount.model.UserAccount;
import com.microuseraccount.repository.AccountRepository;
import com.microuseraccount.repository.UserAccountRepository;
import com.microuseraccount.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service("userAccountService")
public class UserAccountService{
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
		
	@Transactional
	public void updateSaldo(Double saldo, long accountId) {
		// UserAccount userAccount = userAccountRepository.findById(accountId).orElseThrow(
		// 	() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		// userAccount.setSaldo(saldo);
		// userAccountRepository.save(userAccount);
	}

	@Transactional(readOnly = true)
	public Double getSaldo(long accountId) {
		User user = userRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		return userAccountRepository.findByUser(user).orElseThrow().getSaldo();
	}
}
