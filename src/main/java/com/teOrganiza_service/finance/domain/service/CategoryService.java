package com.teOrganiza_service.finance.domain.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Category;
import com.teOrganiza_service.finance.domain.model.dto.CategoryDto;

@Service
public interface CategoryService {
	Category save(CategoryDto category);
	Category update(CategoryDto category);
	Category findById(UUID id);
	List<Category> findByUserId(UUID userId);
	void delete(UUID id);
	void createCategotyForNewUser(UUID userId);
}
