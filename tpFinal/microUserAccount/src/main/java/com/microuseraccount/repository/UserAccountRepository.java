package com.microuseraccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microuseraccount.model.UserAccount;

@Repository ("UserAccountRepository")
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}

