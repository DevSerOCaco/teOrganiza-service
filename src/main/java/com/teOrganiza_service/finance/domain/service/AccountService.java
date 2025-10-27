package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;

@Service
public interface AccountService {
	
	Account save(CreateAccountDto account);
	Account update(AccountDto account);
	Account findById(UUID id);
	List<Account> findByUserId(UUID userId);
	void delete(UUID id);
	void createAccountForNewUser(UUID userId);
	
}
