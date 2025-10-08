package com.teOrganiza_service.identity.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teOrganiza_service.identity.domain.model.Role;
import com.teOrganiza_service.identity.domain.model.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}