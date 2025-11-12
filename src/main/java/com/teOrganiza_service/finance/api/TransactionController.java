package com.teOrganiza_service.finance.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.TransactionDto;
import com.teOrganiza_service.finance.domain.service.TransactionService;
import com.teOrganiza_service.identity.infrastructure.security.UserPrincipal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/my-accounts")
	public ResponseEntity<Page<TransactionDto>> findByAuthenticatedUser(@AuthenticationPrincipal UserPrincipal principal, 
			@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<TransactionDto> transactions = transactionService.findAllByUserId(principal.getUserId(), pageable);
		return ResponseEntity.ok(transactions);
	}
	
	@GetMapping("user/{userId}")
	public ResponseEntity<Page<TransactionDto>> findByUserId(@PathVariable UUID userId, 
			Pageable pageable) {
		Page<TransactionDto> transactions = transactionService.findAllByUserId(userId, pageable);
		return ResponseEntity.ok(transactions);
	}

}
