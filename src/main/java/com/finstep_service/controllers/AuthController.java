package com.finstep_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finstep_service.entities.dto.LoginRequest;
import com.finstep_service.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Aqui você valida usuário e senha no banco
        if(request.username().equals("admin") && request.password().equals("1234")) {
            String token = jwtService.gerarToken("admin", List.of("ROLE_ADMIN"));
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/role-test")
    @PreAuthorize("hasRole('ADMIN')")
    public String testeRole() {
        return "Você é admin!";
    }
}
