package com.teOrganiza_service.identity.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.identity.domain.model.Role;
import com.teOrganiza_service.identity.domain.model.RoleType;
import com.teOrganiza_service.identity.domain.repository.RoleRepository;

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