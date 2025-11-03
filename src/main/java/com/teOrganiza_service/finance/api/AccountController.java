package com.teOrganiza_service.finance.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teOrganiza_service.finance.domain.model.dto.AccountDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.service.AccountService;
import com.teOrganiza_service.identity.domain.model.User;
import com.teOrganiza_service.identity.domain.service.UserService;

@RestController()
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<AccountDto>> findAll(Pageable pageable) {
		Page<AccountDto> accounts = accountService.findAll(pageable);
		return ResponseEntity.ok(accounts);
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> save(@RequestBody CreateAccountDto account) {
		AccountDto savedAccount = accountService.save(account);
		return ResponseEntity.ok(savedAccount);
	}
	
	@PutMapping
	public ResponseEntity<AccountDto> update(@RequestBody AccountDto account) {
		AccountDto updatedAccount = accountService.update(account);
		return ResponseEntity.ok(updatedAccount);
	}
	
	@GetMapping("/my-accounts")
	public ResponseEntity<Page<AccountDto>> findByAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails, 
			@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
		
		User user = userService.findByEmail(userDetails.getUsername());
		Pageable pageable = PageRequest.of(page, size);
		Page<AccountDto> accounts = accountService.findByUserId(user.getUserId(), page, size);
		return ResponseEntity.ok(accounts);
	}
}
