package com.teOrganiza_service.identity.domain.service;

import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.identity.api.dto.CreateUserDto;
import com.teOrganiza_service.identity.domain.model.User;


@Service
public interface UserService {

	User save(CreateUserDto user);
	User update(CreateUserDto user);
	User findByEmail(String email);
	void delete(CreateUserDto user);
	public User processOAuth2User(OAuth2User oAuth2User);
}