package com.finstep_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finstep_service.controllers.dtos.JwtAuthenticationResponse;
import com.finstep_service.controllers.dtos.LoginRequest;
import com.finstep_service.entities.User;
import com.finstep_service.security.JwtTokenProvider;
import com.finstep_service.services.UserServiceImpl;

@RestController()
public class AuthController {
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;

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
         return ResponseEntity.ok(user.toString());
    }
    
    
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Aqui a lógica para gerar o token é um pouco diferente, pois o Principal não é um OAuth2User
        String jwt = tokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
   
}