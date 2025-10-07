package com.teOrganiza_service.config;

import com.teOrganiza_service.entities.Role;
import com.teOrganiza_service.entities.types.RoleType;
import com.teOrganiza_service.repository.RoleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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