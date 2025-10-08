package com.teOrganiza_service.identity.domain.service;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.identity.domain.model.Role;
import com.teOrganiza_service.identity.domain.model.RoleType;
@Service
public interface RoleService {
	Role findOrCreateByName(RoleType roleName);
}