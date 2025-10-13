package com.teOrganiza_service.finance.domain.service;


import java.util.List;
import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.Category;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.model.dto.UpdateAccountDto;

public interface CategoryService {
	Category save(CreateAccountDto account);
	Category update(UpdateAccountDto account);
	Category findById(UUID id);
	List<Category> findByUserId(UUID userId);
	void delete(UUID id);
	void createAccountForNewUser(UUID userId);
}
