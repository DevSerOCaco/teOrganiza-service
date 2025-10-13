package com.teOrganiza_service.finance.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.model.dto.UpdateAccountDto;
import com.teOrganiza_service.finance.domain.repository.AccountRepository;
import com.teOrganiza_service.identity.api.dto.CreateUserDto;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account save(CreateAccountDto account) {
		Account newAccount = new Account();
		newAccount.setUserId(account.userId());
		newAccount.setName(account.name());
		newAccount.setBalance(account.initialBalance());
		return accountRepository.save(newAccount);
	}

	@Override
	public Account update(UpdateAccountDto account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAccountForNewUser(UUID userId) {
		CreateAccountDto newAccount = new CreateAccountDto(userId, "Wallet",  BigDecimal.valueOf(0));
		this.save(newAccount);
	}

	
}
