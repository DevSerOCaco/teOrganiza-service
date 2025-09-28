package com.finstep_service.services;


import com.finstep_service.entities.AuthProvider;
import com.finstep_service.entities.Role;
import com.finstep_service.entities.User;
import com.finstep_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
    
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
    
    @Transactional
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
            Role userRole = roleService.findOrCreateByName("ROLE_USER");
            newUser.setRoles(Set.of(userRole));
            
            return userRepository.save(newUser);
        }
    }
}