package com.microuseraccount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microuseraccount.model.Account;
import com.microuseraccount.model.UserAccount;
import com.microuseraccount.repository.UserAccountRepository;

@Service("userAccountService")
public class UserAccountService{
    @Autowired
    private UserAccountRepository userAccountRepository;
	
}
