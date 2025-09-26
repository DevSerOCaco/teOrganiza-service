package com.finstep_service.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.finstep_service.security.JwtTokenProvider;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

 private final JwtTokenProvider tokenProvider = new JwtTokenProvider();

 @Override
 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
     // Gera o token JWT
     String token = tokenProvider.generateToken(authentication);

     // URL de callback do seu frontend React
     String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/login/callback")
             .queryParam("token", token)
             .build().toUriString();
     
     getRedirectStrategy().sendRedirect(request, response, targetUrl);
 }
}