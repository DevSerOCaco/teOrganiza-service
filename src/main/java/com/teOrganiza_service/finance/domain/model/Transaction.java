package com.teOrganiza_service.finance.domain.model;

import com.teOrganiza_service.finance.domain.model.types.TransactionStatus;
import com.teOrganiza_service.finance.domain.model.types.TransactionType;
import com.teOrganiza_service.identity.domain.model.User;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transactions", schema = "finance")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /**
     * Data de vencimento (para PENDING) ou data de liquidação (para SETTLED).
     */
    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionType type;

    /**
     * Campo-chave para sua lógica de "Contas a Pagar".
     * PENDING = A pagar / A receber
     * SETTLED = Paga / Recebida
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionStatus status;

    // --- Relacionamentos Essenciais ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // --- Relacionamentos Opcionais (Origem) ---
    /**
     * Se esta transação faz parte de um parcelamento ou grupo,
     * este campo será preenchido.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_group_id") // nullable = true (padrão)
    private TransactionGroup transactionGroup;

    /**
     * Se esta transação foi gerada automaticamente por uma regra de recorrência,
     * este campo será preenchido. Útil para rastreamento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurrence_rule_id") // nullable = true (padrão)
    private RecurrenceRule recurrenceRule;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payment> payments = new java.util.ArrayList<>();

    // --- Construtores, Getters e Setters ---
    
    public Transaction() {
    }

    // Exemplo de método utilitário para "liquidar" uma transação
    public void settleTransaction() {
        this.status = TransactionStatus.SETTLED;
        // Aqui você poderia, por exemplo, atualizar o saldo da Account
        // (embora essa lógica geralmente fique no Service)
    }

    @Transient
    public BigDecimal getPaidAmount() {
        return payments.stream()
                       .map(Payment::getAmount)
                       .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transient
    public BigDecimal getBalance() {
        return getAmount().subtract(getPaidAmount());
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
    
	
	
    public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public java.util.List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(java.util.List<Payment> payments) {
        this.payments = payments;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public TransactionGroup getTransactionGroup() {
		return transactionGroup;
	}

	public void setTransactionGroup(TransactionGroup transactionGroup) {
		this.transactionGroup = transactionGroup;
	}

	public RecurrenceRule getRecurrenceRule() {
		return recurrenceRule;
	}

	public void setRecurrenceRule(RecurrenceRule recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}
    
    
}