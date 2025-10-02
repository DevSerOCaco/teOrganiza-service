package com.finstep_service.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.finstep_service.security.JwtTokenProvider;
import com.finstep_service.services.UserServiceImpl;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserServiceImpl userService; 

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // **Processa o usuário (cria ou busca)**
        userService.processOAuth2User(oAuth2User);

        // Gera o token JWT (a lógica aqui pode ser a mesma ou adaptada)
        String token = tokenProvider.generateToken(authentication);

        // URL de callback do seu frontend React
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/login/callback")
                .queryParam("token", token)
                .build().toUriString();
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}