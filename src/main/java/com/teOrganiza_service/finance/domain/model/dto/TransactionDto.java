package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.types.TransactionStatus;
import com.teOrganiza_service.finance.domain.model.types.TransactionType;

public record TransactionDto(
	    UUID id,
	    UUID userId,
	    String description,
	    BigDecimal amount,
	    LocalDate date,
	    TransactionType type,
	    TransactionStatus status,
	    UUID accountId,
	    UUID categoryId,
	    UUID transactionGroupId,
	    UUID recurrenceRuleId,
	    List<PaymentDto> payments
	) {
	}