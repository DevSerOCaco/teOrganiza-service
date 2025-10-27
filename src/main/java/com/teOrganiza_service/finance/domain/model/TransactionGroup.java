package com.teOrganiza_service.finance.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.teOrganiza_service.identity.domain.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_groups", schema = "finance")
public class TransactionGroup implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "user_id")
    private UUID userId;

    /**
     * Descrição para identificar o grupo.
     * Ex: "Parcelamento iPhone 15", "Assinatura Netflix"
     */
    @Column(nullable = false, length = 100)
    private String description;


    /**
     * Lista de transações que pertencem a este grupo.
     * O 'mappedBy' indica que a entidade Transaction é a dona do relacionamento.
     */
    @OneToMany(mappedBy = "transactionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    // --- Construtores, Getters e Setters ---

    public TransactionGroup() {
    }

    // Você pode adicionar métodos utilitários se precisar
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setTransactionGroup(this);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
    
    
}
