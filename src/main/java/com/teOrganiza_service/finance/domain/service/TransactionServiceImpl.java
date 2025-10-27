package com.teOrganiza_service.finance.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.Category;
import com.teOrganiza_service.finance.domain.model.Payment;
import com.teOrganiza_service.finance.domain.model.RecurrenceRule;
import com.teOrganiza_service.finance.domain.model.Transaction;
import com.teOrganiza_service.finance.domain.model.TransactionGroup;
import com.teOrganiza_service.finance.domain.model.dto.CreateTransactionDto;
import com.teOrganiza_service.finance.domain.model.dto.TransactionDto;
import com.teOrganiza_service.finance.domain.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TransactionGroupService transactionGroupService;
	
	@Autowired
	private RecurrenceRuleService recurrenceRuleService;

	@Override
	public Transaction save(CreateTransactionDto dto) {
		
		Transaction newTransaction = new Transaction();
		Account account = this.accountService.findById(dto.accountId());
		Category category = this.categoryService.findById(dto.categoryId());
		TransactionGroup transactionGroup = new TransactionGroup();
		RecurrenceRule recurrenceRule = new RecurrenceRule();
		
		if(dto.transactionGroupId() != null) {
			transactionGroup = transactionGroupService.findById(dto.transactionGroupId());
		}
		
		if(dto.recurrenceRuleId() != null) {
			recurrenceRule =  recurrenceRuleService.findById(dto.recurrenceRuleId());
		}
		
		List<Payment> payments = dto.payments().stream().map(paymentDto -> {
			Payment payment = new Payment();
			payment.setAmount(paymentDto.amount());
			payment.setPaymentDate(paymentDto.paymentDate());
			payment.setDescription(paymentDto.description());
			payment.setUserId(dto.userId());
			payment.setTransaction(newTransaction);
			return payment;
		}).collect(Collectors.toList());
		
		newTransaction.setUserId(dto.userId());
		newTransaction.setDescription(dto.description());
		newTransaction.setAmount(dto.amount());
		newTransaction.setDate(dto.date());
		newTransaction.setType(dto.type());
		newTransaction.setStatus(dto.status());
		newTransaction.setAccount(account);
		newTransaction.setCategory(category);
		newTransaction.setTransactionGroup(transactionGroup);
		newTransaction.setRecurrenceRule(recurrenceRule);
		
		
		newTransaction.setPayments(payments);
		return this.transactionRepository.save(newTransaction);
	}

	@Override
	public Transaction update(TransactionDto dto) {
		
		Transaction transaction = this.transactionRepository.findById(dto.id())
		.orElseThrow(() -> new RuntimeException("Transaction not found"));

		Account account = this.accountService.findById(dto.accountId());
		Category category = this.categoryService.findById(dto.categoryId());
		TransactionGroup transactionGroup = new TransactionGroup();
		RecurrenceRule recurrenceRule = new RecurrenceRule();

		if(dto.transactionGroupId() != null) {
			transactionGroup = transactionGroupService.findById(dto.transactionGroupId());
		}

		if(dto.recurrenceRuleId() != null) {
			recurrenceRule =  recurrenceRuleService.findById(dto.recurrenceRuleId());
		}

		transaction.setDescription(dto.description());
		transaction.setAmount(dto.amount());
		transaction.setDate(dto.date());
		transaction.setType(dto.type());
		transaction.setStatus(dto.status());
		transaction.setAccount(account);
		transaction.setCategory(category);
		transaction.setTransactionGroup(transactionGroup);
		transaction.setRecurrenceRule(recurrenceRule);

		return this.transactionRepository.save(transaction);
	}

	@Override
	public Optional<Transaction> findById(UUID id) {
		return this.transactionRepository.findById(id);
	}

	@Override
	public Page<Transaction> findAllByUserId(UUID userId,  Pageable pageable) {
		return this.transactionRepository.findAllByUserId(userId, pageable);
	}

	@Override
	public void delete(UUID id) {
		this.transactionRepository.deleteById(id);
	}

}
