package com.teOrganiza_service.finance.domain.model.dto;

import java.util.UUID;

public record CategoryDto(UUID id, UUID userId, String name,UUID fatherId) {

}
