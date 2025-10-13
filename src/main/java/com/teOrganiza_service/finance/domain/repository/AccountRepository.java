package com.teOrganiza_service.finance.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teOrganiza_service.finance.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> { 

}
