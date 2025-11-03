package com.teOrganiza_service.finance.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Page<AccountDto> findAll(Pageable pageable) {
		Page<Account> account = accountRepository.findAll(pageable);
		return account.map(AccountDto::fromEtity);
	}

	@Override
	public AccountDto save(CreateAccountDto account) {
		Account newAccount = new Account();
		newAccount.setUserId(account.userId());
		newAccount.setName(account.name());
		newAccount.setBalance(account.initialBalance());
		return AccountDto.fromEtity(accountRepository.save(newAccount)); 
	}

	@Override
	public AccountDto update(AccountDto dto) {
		Account account = accountRepository.findById(dto.id())
				.orElseThrow(() -> new RuntimeException("Transaction not found"));;
		if(dto.balance() != null) {
			account.setBalance(dto.balance());
		}
		if(dto.name() != null) {
			account.setName(dto.name());
		}
		return AccountDto.fromEtity(accountRepository.save(account));
	}
	

	@Override
	public AccountDto findById(UUID id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.map(AccountDto::fromEtity).orElse(null);
	}

	@Override
	public Page<AccountDto> findByUserId(UUID userId, int page, int size) {
		
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Account> account = accountRepository.findByUserId(userId, pageable);
        return account.map(AccountDto::fromEtity);
	}

	@Override
	public void delete(UUID id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void createAccountForNewUser(UUID userId) {
		CreateAccountDto newAccount = new CreateAccountDto(userId, "Wallet",  BigDecimal.valueOf(0));
		this.save(newAccount);
	}

	
}
