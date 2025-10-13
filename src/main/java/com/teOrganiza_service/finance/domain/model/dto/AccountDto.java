package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(UUID id,UUID userId, String name, BigDecimal balance) {

}
