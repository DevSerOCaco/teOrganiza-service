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

	TransactionDto save(CreateTransactionDto dto);
	TransactionDto update(TransactionDto dto);
	Optional<TransactionDto> findById(UUID id);
	void delete(UUID id);
	Page<TransactionDto> findAllByUserId(UUID userId, Pageable pageable);
}
