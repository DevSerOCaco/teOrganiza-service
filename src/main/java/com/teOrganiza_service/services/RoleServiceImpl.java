package com.teOrganiza_service.services;

import com.teOrganiza_service.entities.Role;
import com.teOrganiza_service.entities.types.RoleType;
import com.teOrganiza_service.repository.RoleRepository;

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