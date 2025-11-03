package com.teOrganiza_service.finance.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teOrganiza_service.finance.domain.model.dto.TransactionDto;
import com.teOrganiza_service.finance.domain.service.TransactionService;

@RestController()
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("user/{userId}")
	public ResponseEntity<Page<TransactionDto>> findByUserId(@PathVariable UUID userId, 
			Pageable pageable) {
		Page<TransactionDto> transactions = transactionService.findAllByUserId(userId, pageable);
		return ResponseEntity.ok(transactions);
	}

}
