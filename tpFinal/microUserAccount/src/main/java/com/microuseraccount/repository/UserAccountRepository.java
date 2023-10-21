package com.microuseraccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microuseraccount.model.Account;
import com.microuseraccount.model.User;
import com.microuseraccount.model.UserAccount;
import com.microuseraccount.model.UserAccountPK;

@Repository("userAccountRepository")
public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccountPK> {
    Optional<UserAccount> findByUserAndAccount(User user, Account account);
    void deleteByUserAndAccount(User user, Account account);
    UserAccount save(UserAccount userAccount);
    Optional<UserAccount> findByUser(User user);
}


