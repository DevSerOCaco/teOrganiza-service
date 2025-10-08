package com.finstep_service.identity.infrastructure.service;

import com.finstep_service.identity.domain.model.Role;
import com.finstep_service.identity.domain.model.RoleType;
import com.finstep_service.identity.domain.repository.RoleRepository;
import com.finstep_service.identity.domain.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
    private  RoleRepository roleRepository;

	@Override
    public Role findOrCreateByName(RoleType roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });
    }
}