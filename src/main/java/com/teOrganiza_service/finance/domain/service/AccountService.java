package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;

@Service
public interface AccountService {
	
	AccountDto save(CreateAccountDto account);
	AccountDto update(AccountDto account);
	AccountDto findById(UUID id);
	Page<AccountDto> findAll(Pageable pageable);
	public Page<AccountDto> findByUserId(UUID userId, int page, int size);
	void delete(UUID id);
	void createAccountForNewUser(UUID userId);
	
}
