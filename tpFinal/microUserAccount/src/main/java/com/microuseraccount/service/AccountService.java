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

/**
 * AccountService
 * Servicio de la entidad Account.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service("accountService")
public class AccountService{
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
		
	/**
	 * findAll
	 * Devuelve todas las cuentas.
	 * @return List<AccountDTO>
	 */
	@Transactional(readOnly = true)
	public List<AccountDTO> findAll() {
		return this.accountRepository.findAll().stream().map(AccountDTO::new ).toList();
	}

	/**
	 * findById
	 * Devuelve una cuenta por id.
	 * @param id
	 * @return AccountDTO
	 */
	@Transactional(readOnly = true)
	public AccountDTO findById(Long id) {
		return this.accountRepository.findById(id).map(AccountDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
	}
	
	/**
	 * save
	 * Crea una nueva cuenta.
	 * @param entity
	 * @return AccountDTO
	 */
	@Transactional
	public AccountDTO save(AccountDTO entity) {
		return new AccountDTO(this.accountRepository.save(new Account(entity)));
	}

	/**
	 * delete
	 * Elimina una cuenta por id.
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		accountRepository.delete(accountRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id)));
	}

	/**
	 * update
	 * Actualiza una cuenta por id.
	 * @param id
	 * @param entity
	 */
	@Transactional
	public void update(Long id, AccountDTO entity) {
		Account account = accountRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
			account.setFechaAlta(entity.getFechaAlta());
			account.setHabilitada(entity.isHabilitada());
			account.setIdMPago(entity.getIdMPago());
			account.setSaldo(entity.getSaldo());
			accountRepository.save(account);
		}
		
	/**
	 * asociarUsuario
	 * Vincula una cuenta a un usuario.
	 * @param userId
	 * @param accountId
	 */
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
	
	/**
	 * desvincularUsuario
	 * Desvincula una cuenta de un usuario.
	 * @param userId
	 * @param accountId
	 */
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

	/**
	 * updateSaldo
	 * Actualiza el saldo de una cuenta.
	 * @param saldo
	 * @param accountId
	 */
	@Transactional
	public void updateSaldo(Double saldo, long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		account.setSaldo(saldo);
		accountRepository.save(account);
	}

	/**
	 * getSaldo
	 * Metodo que obtiene el saldo de una cuenta.
	 * @param accountId
	 * @return Double
	 */
	@Transactional(readOnly = true)
	public Double getSaldo(long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + accountId));
		return accountRepository.findById(account.getAccountId()).orElseThrow().getSaldo();
	}
	
	/**
	 * suspendAccount
	 * Suspende temporalmente una cuenta.
	 * @param accountId
	 */
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

	/**
	 * activateAccount
	 * Activa una cuenta previamente suspendida.
	 * @param accountId
	 */
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

	/**
	 * getCuentasByUserId
	 * Devuelve las cuentas de un usuario.
	 * @param userId
	 * @return List<AccountDTO>
	 */
	@Transactional(readOnly = true)
    public List<AccountDTO> getCuentasByUserId(long userId) {
		return this.accountRepository.findByUserId(userId);
    }
}
