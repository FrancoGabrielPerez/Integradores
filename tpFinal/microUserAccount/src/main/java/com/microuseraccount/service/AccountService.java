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

@Service("accountService")
public class AccountService{
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
		
	@Transactional(readOnly = true)
	public List<AccountDTO> findAll() {
		return this.accountRepository.findAll().stream().map(AccountDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public AccountDTO findById(Long id) {
		return this.accountRepository.findById(id).map(AccountDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
	}
	
	@Transactional
	public AccountDTO save(AccountDTO entity) {
		return new AccountDTO(this.accountRepository.save(new Account(entity)));
	}

	@Transactional
	public void delete(Long id) {
		accountRepository.delete(accountRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id)));
	}

	@Transactional
	public void update(Long id, AccountDTO entity) {
		Account account = accountRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
			account.setFechaAlta(entity.getFechaAlta());
			account.setHabilitada(entity.isHabilitada());
			account.setIdMPago(entity.getIdMPago());
			accountRepository.save(account);
		}
		
	@Transactional
	public void asociarUsuario(long userId, long accountId) {
		Objects.requireNonNull(userId);
		Objects.requireNonNull(accountId);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + userId));

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + accountId));

		if (userAccountRepository.findByUserAndAccount(user, account).isPresent()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra asociada al usuario");
		}

		UserAccount nuevo = new UserAccount(user, account);
		userAccountRepository.save(nuevo);
	}
	
	@Transactional
	public void desvincularUsuario(long userId, long accountId) {
		Objects.requireNonNull(userId);
		Objects.requireNonNull(accountId);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + userId));

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + accountId));

		userAccountRepository.deleteByUserAndAccount(user, account);
	}

	@Transactional
	public void updateSaldo(Double saldo, long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		account.setSaldo(saldo);
		accountRepository.save(account);
	}

	@Transactional(readOnly = true)
	public Double getSaldo(long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		return accountRepository.findById(account.getAccountId()).orElseThrow().getSaldo();
	}
	
	@Transactional
	public void suspendAccount(long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		if (!account.isHabilitada()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra suspendida");
		}
		account.setHabilitada(false);
		accountRepository.save(account);
	}

	@Transactional
	public void activateAccount(long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		if (account.isHabilitada()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra activa");
		}
		account.setHabilitada(true);
		accountRepository.save(account);
	}

	@Transactional(readOnly = true)
    public List<AccountDTO> getCuentasByUserId(long userId) {
		return this.accountRepository.findByUserId(userId);
    }


	/*
	@Transactional(readOnly = true)
	public List<InformeAccountDTO> informeAccounts() {
		return this.inscriptos.informeAccounts();
	}

	@Transactional(readOnly = true)
	public List<InformeAccountCantEstudiantesDTO> accountsOrdenadas() {
		return this.accountRepository.accountsOrdenadas();
	} */
}
