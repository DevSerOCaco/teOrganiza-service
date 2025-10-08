package com.teOrganiza_service.shared.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.teOrganiza_service.identity.domain.model.Role;
import com.teOrganiza_service.identity.domain.model.RoleType;
import com.teOrganiza_service.identity.domain.repository.RoleRepository;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleType.values()).forEach(roleType -> {
            if (roleRepository.findByName(roleType).isEmpty()) {
                roleRepository.save(new Role(roleType));
                System.out.println("Criando role: " + roleType.name());
            }
        });
    }
}