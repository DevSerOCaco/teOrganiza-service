package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentDto(
    BigDecimal amount,
    LocalDate paymentDate,
    String description
) {

}