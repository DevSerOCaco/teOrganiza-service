package com.finstep_service.services;

import org.springframework.stereotype.Service;

import com.finstep_service.entities.Role;
import com.finstep_service.entities.types.RoleType;
@Service
public interface RoleService {
	Role findOrCreateByName(RoleType roleName);
}
