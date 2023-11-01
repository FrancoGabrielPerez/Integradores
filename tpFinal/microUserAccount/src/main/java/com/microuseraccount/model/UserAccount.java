package com.microuseraccount.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Objects;

@Entity
@Table(name = "user_account")
@Data
@IdClass(UserAccountID.class)
public class UserAccount {	
	@Id
	private User user;

	@Id
	private Account account;

	public UserAccount(){
		super();
	}

	public UserAccount(User user, Account account) {
		this.user = Objects.requireNonNull(user, "user must not be null");
		this.account = Objects.requireNonNull(account, "account must not be null");
	}
}