package com.finstep_service.identity.infrastructure.security;


import com.finstep_service.identity.domain.model.User; // Sua entidade
import com.finstep_service.identity.domain.repository.UserRepository;

// Imports do Spring Security
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Busca sua entidade User do banco de dados
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // 2. Mapeia o Set<Role> da sua entidade para um Set<GrantedAuthority>
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
        
        // --- LÓGICA PARA TRATAR A SENHA NULA ---
        // Se a senha do usuário no banco for nula (caso de login com Google),
        // nós geramos uma senha aleatória e segura apenas para construir o objeto UserDetails.
        // Esta senha NUNCA é usada para validação.
        String password = user.getPassword() != null ? user.getPassword() : UUID.randomUUID().toString();

        // 3. Retorna um objeto UserDetails que o Spring Security entende
        //    Passamos o email, a senha e a lista de authorities.
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            password,
            authorities
        );
    }
}