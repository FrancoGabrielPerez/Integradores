package com.microuseraccount.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "user_account")
@Data
@IdClass(UserAccountPK.class)
public class UserAccount {
	@Column(name = "saldo", nullable = false)
	private Double saldo;
	
	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn()
	private User userId;

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn()
	private Account accountId;

	public UserAccount(){
		super();
	}

	public UserAccount(User user, Account account) {
		this.userId = Objects.requireNonNull(user, "user must not be null");
		this.accountId = Objects.requireNonNull(account, "account must not be null");
	}
}