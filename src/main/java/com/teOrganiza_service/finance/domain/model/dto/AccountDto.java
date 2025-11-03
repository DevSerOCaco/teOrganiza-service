package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.Account;

public record AccountDto(UUID id,UUID userId, String name, BigDecimal balance) {

	public static AccountDto fromEtity(Account account) {
		return new AccountDto(
				account.getId(), 
				account.getUserId(), 
				account.getName(), 
				account.getBalance());
	}
	
	public Account toEntity() {
		return new Account(id, userId, name, balance);
	}
}
