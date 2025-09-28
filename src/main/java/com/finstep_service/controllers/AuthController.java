package com.finstep_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finstep_service.entities.User;
import com.finstep_service.services.UserService;

@RestController()
public class AuthController {
	
	@Autowired
	private UserService userService;

    @GetMapping("/home")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "Bem-vindo! Você está logado.";
        }
        return "Olá, visitante! Faça o login para acessar o conteúdo.";
    }

    @GetMapping("/user/me")
    public ResponseEntity<?> user(@AuthenticationPrincipal UserDetails userDetails) {
    	 if (userDetails == null) {
             return ResponseEntity.status(401).body("Usuário não autenticado.");
         }
         String email = userDetails.getUsername();
         User user = userService.findByEmail(email);
         return ResponseEntity.ok(user);
    }
    
    /*
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Aqui a lógica para gerar o token é um pouco diferente, pois o Principal não é um OAuth2User
        String jwt = tokenProvider.generateTokenFromUserDetails((UserDetails) authentication.getPrincipal());
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    */
}