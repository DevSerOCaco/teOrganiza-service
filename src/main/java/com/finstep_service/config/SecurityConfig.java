package com.finstep_service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler = new OAuth2LoginSuccessHandler();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // Vamos usar JWT, então a política de sessão deve ser STATELESS
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                // Rotas públicas: login, autorização oauth2, etc.
                .requestMatchers("/", "/login**", "/error", "/oauth2/**").permitAll()
                // Todas as outras rotas exigem autenticação
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                // Usamos nosso handler customizado em vez do .defaultSuccessUrl()
                .successHandler(oAuth2LoginSuccessHandler)
            );
            
        // Aqui você adicionaria o filtro de validação JWT (ver próximo passo)
        // http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}