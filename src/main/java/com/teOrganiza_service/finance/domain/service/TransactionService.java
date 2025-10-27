package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teOrganiza_service.finance.domain.model.Transaction;
import com.teOrganiza_service.finance.domain.model.dto.CreateTransactionDto;
import com.teOrganiza_service.finance.domain.model.dto.TransactionDto;

public interface TransactionService {

	Transaction save(CreateTransactionDto dto);
	Transaction update(TransactionDto dto);
	Optional<Transaction> findById(UUID id);
	void delete(UUID id);
	Page<Transaction> findAllByUserId(UUID userId, Pageable pageable);
}
