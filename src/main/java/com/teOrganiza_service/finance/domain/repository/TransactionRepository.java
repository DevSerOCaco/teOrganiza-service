package com.teOrganiza_service.finance.domain.repository;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teOrganiza_service.finance.domain.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

	Page<Transaction> findAllByUserId(UUID userId, Pageable pageable);
}
