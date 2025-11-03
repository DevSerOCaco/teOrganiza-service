package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.Category;
import com.teOrganiza_service.finance.domain.model.RecurrenceRule;
import com.teOrganiza_service.finance.domain.model.Transaction;
import com.teOrganiza_service.finance.domain.model.TransactionGroup;
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
	
	public static TransactionDto fromEntity(Transaction transaction) {
		return new TransactionDto(
				transaction.getId(),
				transaction.getUserId(),
				transaction.getDescription(),
				transaction.getAmount(),
				transaction.getDate(),
				transaction.getType(),
				transaction.getStatus(),
				transaction.getAccount().getId(),
				transaction.getCategory().getId(),
				transaction.getTransactionGroup().getId(),
				transaction.getRecurrenceRule().getId(),
				transaction.getPayments().stream().map(PaymentDto::fromEntity).toList());
	}
	
	public Transaction toEntity() {
			Transaction transaction = new Transaction();
			transaction.setId(this.id);
			transaction.setUserId(this.userId);
			transaction.setDescription(this.description);
			transaction.setAmount(this.amount);
			transaction.setDate(this.date);
			transaction.setType(this.type);
			transaction.setStatus(this.status);
			if(this.accountId != null) {
				Account accountStub = new Account();
				accountStub.setId(this.accountId);
				transaction.setAccount(accountStub);
			}
			if(this.categoryId != null) {
				Category categoryStub = new Category();
				categoryStub.setId(this.categoryId);
				transaction.setCategory(categoryStub);
			}
			if(this.transactionGroupId != null) {
				TransactionGroup transactionGroupStub = new TransactionGroup();
				transactionGroupStub.setId(this.transactionGroupId);
				transaction.setTransactionGroup(transactionGroupStub);
			}
			if(this.recurrenceRuleId != null) {
				RecurrenceRule recurrenceRuleStub = new RecurrenceRule();
				recurrenceRuleStub.setId(this.recurrenceRuleId);
				transaction.setRecurrenceRule(recurrenceRuleStub);
			}
			return transaction;	
		}
	}
	