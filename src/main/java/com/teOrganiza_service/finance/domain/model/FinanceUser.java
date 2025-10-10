package com.teOrganiza_service.finance.domain.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "finance_users", schema = "finance")
public class FinanceUser {

	@Id
    private Integer id;
    private String nome;
    
    @OneToMany(mappedBy = "usuario")
    private List<Account> contas;
    
    public FinanceUser() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
