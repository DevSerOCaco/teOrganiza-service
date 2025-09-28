package com.finstep_service.services;

import com.finstep_service.entities.Role;
import com.finstep_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Busca um papel pelo nome. Se não encontrar, cria um novo,
     * o salva no banco de dados e o retorna.
     *
     * @param roleName O nome do papel (ex: "ROLE_USER")
     * @return A entidade Role correspondente.
     */
    public Role findOrCreateByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });
    }
}