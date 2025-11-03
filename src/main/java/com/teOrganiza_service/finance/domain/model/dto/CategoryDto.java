package com.teOrganiza_service.finance.domain.model.dto;

import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.Category;

public record CategoryDto(UUID id, UUID userId, String name,UUID fatherId) {

	public static CategoryDto fromEtity(Category category) {
		return new CategoryDto(
				category.getId(),
				category.getUserId(),
				category.getName(),
				category.getFatherCategory().getId());
	}
	
	public static CategoryDto fromEtityWithoutFather(Category category) {
		return new CategoryDto(
				category.getId(),
				category.getUserId(),
				category.getName(),
				null);
	}
	
	public Category toEntity() {
		return new Category(
				id,
				userId,
				name);
	}
}
