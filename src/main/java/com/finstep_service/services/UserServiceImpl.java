package com.finstep_service.services;


import com.finstep_service.controllers.dtos.CreateUserDto;
import com.finstep_service.entities.Role;
import com.finstep_service.entities.User;
import com.finstep_service.entities.types.AuthProvider;
import com.finstep_service.entities.types.RoleType;
import com.finstep_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
    
    @Transactional
    @Override
    public User processOAuth2User(OAuth2User oAuth2User) {
        // O Google provê o email como um atributo padrão
        String email = oAuth2User.getAttribute("email");
        if (email == null) {
            throw new RuntimeException("Email não encontrado no provedor OAuth2");
        }

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
        	 // Lógica para criar um novo usuário
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setImageUrl(oAuth2User.getAttribute("picture"));
            newUser.setProvider(AuthProvider.GOOGLE);
            Role userRole = roleService.findOrCreateByName(RoleType.ROLE_BASIC);
            newUser.setRoles(Set.of(userRole));
            
            return userRepository.save(newUser);
        }
    }

	@Override
	public User save(CreateUserDto userDto) {
		
		var basicRole = roleService.findOrCreateByName(RoleType.ROLE_BASIC);
        var userFromDb = userRepository.findByEmail(userDto.email());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setEmail(userDto.email());
        user.setName(userDto.name());
        user.setFone(userDto.fone());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRoles(Set.of(basicRole));
        user.setProvider(AuthProvider.LOCAL);

        
		return userRepository.save(user);
	}

	@Override
	public User update(CreateUserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CreateUserDto user) {
		// TODO Auto-generated method stub
		
	}
}