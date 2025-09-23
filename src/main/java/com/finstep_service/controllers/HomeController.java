package com.finstep_service.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "Bem-vindo! Você está logado.";
        }
        return "Olá, visitante! Faça o login para acessar o conteúdo.";
    }

    @GetMapping("/user/me")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            return "Nome: " + name + ", Email: " + email;
        }
        return "Informações do usuário não disponíveis.";
    }
}