package com.teOrganiza_service.identity.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.teOrganiza_service.identity.api.dto.CreateUserDto;
import com.teOrganiza_service.identity.domain.model.AuthProvider;
import com.teOrganiza_service.identity.domain.model.Role;
import com.teOrganiza_service.identity.domain.model.RoleType;
import com.teOrganiza_service.identity.domain.model.User;
import com.teOrganiza_service.identity.domain.repository.UserRepository;

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
        	// Lógica pra editar com os dados google um usuario ja existente
            User existingUser = userOptional.get();
            String nameFromGoogle = oAuth2User.getAttribute("name");
            String imageUrlFromGoogle = oAuth2User.getAttribute("picture");
            if (nameFromGoogle != null && !nameFromGoogle.isEmpty()) {
                existingUser.setName(nameFromGoogle);
            }
            if (existingUser.getImageUrl() == null && imageUrlFromGoogle != null) {
                existingUser.setImageUrl(imageUrlFromGoogle);
            }
            return userRepository.save(existingUser);
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
            User existingUser = userFromDb.get();
            // Verifica se o provedor do usuário existente é o Google
            if (existingUser.getProvider() == AuthProvider.GOOGLE) {
                // Lança um erro específico informando a ação correta
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Este e-mail já foi registrado com o Google. Por favor, faça login com o Google.");
            } else {
                // Se o provedor for LOCAL, é uma tentativa de cadastro duplicado
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Este e-mail já está em uso.");
            }
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