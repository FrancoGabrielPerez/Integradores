package com.microuseraccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microuseraccount.repository.UserAccountRepository;

@Service("userAccountService")
public class UserAccountService{
    @Autowired
    private UserAccountRepository userAccountRepository;
	
}
