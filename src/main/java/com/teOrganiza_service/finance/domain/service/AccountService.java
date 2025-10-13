package com.teOrganiza_service.finance.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.model.dto.UpdateAccountDto;

@Service
public interface AccountService {
	
	Account save(CreateAccountDto account);
	Account update(UpdateAccountDto account);
	Account findById(UUID id);
	Account findByUserId(UUID userId);
	void delete(UUID id);
	void createAccountForNewUser(UUID userId);
	
}
