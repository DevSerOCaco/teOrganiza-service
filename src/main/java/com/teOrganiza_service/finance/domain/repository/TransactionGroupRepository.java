package com.teOrganiza_service.finance.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teOrganiza_service.finance.domain.model.TransactionGroup;

@Repository
public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, UUID> {

}
