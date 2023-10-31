package com.microuseraccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
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
    List<UserAccount> findByUser(User user);    

    /*
    @Query("SELECT a FROM Account a WHERE a.id IN (SELECT ua.account_id FROM UserAccount ua WHERE ua.user_id = :userId)")
	List<Account> findByUser(@Param("userId")long userId);
    */
   
    //@Query("SELECT a FROM Account a WHERE a.account_id IN (SELECT ua.account_id FROM UserAccount ua WHERE ua.user_id = :userId)")
    //List<UserAccount> findByUserId(@Param("userId") long userId);

    // @Query("SELECT a FROM Account a WHERE a.id IN (SELECT ua.account.id FROM UserAccount ua WHERE ua.user.id = ?1)")
    // List<Account> find(long userId);
    
}


