package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.TransactionGroup;
import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateTransactionGroupDto;
import com.teOrganiza_service.finance.domain.model.dto.TransactionGroupDto;

@Service
public interface TransactionGroupService {
	
	TransactionGroup save(CreateTransactionGroupDto createTransactionGroupDto);
	TransactionGroup update(TransactionGroupDto transactionGroupDto);
	TransactionGroup findById(UUID id);
	List<TransactionGroup> findByUserId(UUID userId);
	void delete(UUID id);

}
