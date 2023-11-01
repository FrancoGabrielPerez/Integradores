package com.microuseraccount.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microuseraccount.dto.UserDTO;
import com.microuseraccount.model.Account;
import com.microuseraccount.model.User;
import com.microuseraccount.model.UserAccount;
import com.microuseraccount.repository.AccountRepository;
import com.microuseraccount.repository.UserAccountRepository;
import com.microuseraccount.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
@Service("userService")
public class UserService{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
		
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		return this.userRepository.findAll().stream().map(UserDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		return this.userRepository.findById(id).map(UserDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id));
	}
	
	@Transactional
	public UserDTO save(UserDTO entity) {
		return new UserDTO(this.userRepository.save(new User(entity)));
	}

	@Transactional
	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id)));
	}

	@Transactional
	public void update(Long id, UserDTO entity) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id));
		user.setNombre(entity.getNombre());
		user.setApellido(entity.getApellido());
		user.setNroCelular(entity.getNroCelular());
		user.setEmail(entity.getEmail());
		userRepository.save(user);
	}
	
	@Transactional
	public void asociarCuenta(long userId, long accountId) {
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
	public void desvincularCuenta(long userId, long accountId) {
		Objects.requireNonNull(userId);
		Objects.requireNonNull(accountId);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + userId));

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + accountId));

		userAccountRepository.deleteByUserAndAccount(user, account);
	}
}
