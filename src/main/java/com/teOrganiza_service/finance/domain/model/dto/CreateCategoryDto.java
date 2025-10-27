package com.teOrganiza_service.finance.domain.model.dto;

import java.util.UUID;

public record CreateCategoryDto(UUID userId, String name,UUID fatherId) {

}
