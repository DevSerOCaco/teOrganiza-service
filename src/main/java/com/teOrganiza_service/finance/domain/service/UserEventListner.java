package com.teOrganiza_service.finance.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.shared.integration.events.UserCreatedEvent;

import jakarta.transaction.Transactional;

@Service
public class UserEventListner {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	@EventListener
	@Transactional
	public void handleUserCreatedEvent(UserCreatedEvent eventUser) {
		accountService.createAccountForNewUser(eventUser.userId());
		categoryService.createCategotyForNewUser(eventUser.userId());
	}

}
