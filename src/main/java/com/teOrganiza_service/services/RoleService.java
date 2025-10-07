package com.teOrganiza_service.services;

import org.springframework.stereotype.Service;

import com.teOrganiza_service.entities.Role;
import com.teOrganiza_service.entities.types.RoleType;
@Service
public interface RoleService {
	Role findOrCreateByName(RoleType roleName);
}
