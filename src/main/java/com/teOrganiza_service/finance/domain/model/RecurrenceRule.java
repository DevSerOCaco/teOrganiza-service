package com.teOrganiza_service.finance.domain.model;

import com.teOrganiza_service.finance.domain.model.types.RecurrenceFrequency;
import com.teOrganiza_service.finance.domain.model.types.TransactionType;
import com.teOrganiza_service.identity.domain.model.User;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "recurrence_rules")
public class RecurrenceRule implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Dados do "Molde" da Transação ---
    @Column(nullable = false, length = 100)
    private String description;

    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionType type;

    // --- Regras da Recorrência ---
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RecurrenceFrequency frequency;


    @Column(nullable = false)
    private LocalDate startDate; // Data de início da regra

    @Column // Opcional: data final para a regra
    private LocalDate endDate;

    /**
     * Dia do mês para recorrências MENSAIS (1-31).
     * Pode ser nulo se a frequência for outra.
     */
    private Integer dayOfMonth;
    
    @Column(nullable = false)
    private Boolean active = true; // Permite "pausar" a recorrência

    /**
     * Campo de controle para o Job Scheduler.
     * Armazena a data da última transação que foi gerada por esta regra.
     * O Job vai procurar por regras onde nextGenerationDate <= hoje.
     */

    @Column(nullable = false)
    private LocalDate nextGenerationDate;
    
    // --- Relacionamentos ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // A conta padrão para esta recorrência

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // A categoria padrão
    
    /**
     * A regra também pode estar ligada a um grupo, para que todas as 
     * transações geradas por ela (ex: Netflix) fiquem agrupadas.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_group_id", nullable = false)
    private TransactionGroup transactionGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public RecurrenceFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(RecurrenceFrequency frequency) {
		this.frequency = frequency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDate getNextGenerationDate() {
		return nextGenerationDate;
	}

	public void setNextGenerationDate(LocalDate nextGenerationDate) {
		this.nextGenerationDate = nextGenerationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

    
}