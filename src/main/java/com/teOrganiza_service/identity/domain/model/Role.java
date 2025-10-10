package com.teOrganiza_service.identity.domain.model;

import org.springframework.security.core.GrantedAuthority;

import com.teOrganiza_service.identity.domain.model.RoleType;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles", schema = "identity")
public class Role  implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private RoleType name;

    public Role() {}

    public Role(RoleType name) {
        this.name = name;
    }

    
    @Override
    public String getAuthority() {
        return this.name.name(); // Retorna o nome do enum como String (ex: "ROLE_ADMIN")
    }
    
    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}