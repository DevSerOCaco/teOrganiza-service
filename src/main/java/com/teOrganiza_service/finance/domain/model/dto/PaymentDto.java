package com.teOrganiza_service.finance.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teOrganiza_service.finance.domain.model.Account;
import com.teOrganiza_service.finance.domain.model.Payment;
import com.teOrganiza_service.finance.domain.model.Transaction;

public record PaymentDto(
	UUID id,
	UUID userId,
	UUID transactionId,
    BigDecimal amount,
    LocalDate paymentDate,
    String description,
    LocalDateTime createdAt
) {
	
	public static PaymentDto fromEntity(Payment payment) {
		return new PaymentDto(
				payment.getId(),
				payment.getUserId(),
				payment.getTransaction().getId(),
				payment.getAmount(),
				payment.getPaymentDate(),
				payment.getDescription(),
				payment.getCreatedAt());
	}
	
	public Payment toEntity() {
        Payment payment = new Payment();
        payment.setUserId(this.userId);
        payment.setAmount(this.amount);
        payment.setPaymentDate(this.paymentDate);
        payment.setDescription(this.description);

        if (this.transactionId != null) {
            Transaction transactionStub = new Transaction();
            transactionStub.setId(this.transactionId);
            payment.setTransaction(transactionStub);
        }
        return payment;
    }

}