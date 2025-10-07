package com.teOrganiza_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teOrganiza_service.controllers.dtos.CreateUserDto;
import com.teOrganiza_service.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	
    @PostMapping("/register")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {
    	this.userService.save(dto);
		return ResponseEntity.ok().build();
	}

	
}
