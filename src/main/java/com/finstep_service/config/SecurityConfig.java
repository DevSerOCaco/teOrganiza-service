package com.finstep_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para a integração com o React
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login**", "/error").permitAll() // Rotas públicas
                .anyRequest().authenticated() // Todas as outras rotas exigem autenticação
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/home", true) // Redireciona para a página inicial após login
            );

        return http.build();
    }
}