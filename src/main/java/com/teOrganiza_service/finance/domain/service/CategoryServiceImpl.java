package com.teOrganiza_service.finance.domain.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teOrganiza_service.finance.domain.model.Category;
import com.teOrganiza_service.finance.domain.model.dto.CategoryDto;
import com.teOrganiza_service.finance.domain.model.dto.CreateAccountDto;
import com.teOrganiza_service.finance.domain.model.dto.UpdateAccountDto;
import com.teOrganiza_service.finance.domain.model.types.DefaultCategoryType;
import com.teOrganiza_service.finance.domain.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category save(CategoryDto category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(CategoryDto category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCategotyForNewUser(UUID userId) {
	
        Map<DefaultCategoryType, Category> createdCategories = new EnumMap<>(DefaultCategoryType.class);

        for (DefaultCategoryType catEnum : DefaultCategoryType.values()) {
            garantirCriacaoCategoria(catEnum, userId, createdCategories);
        }
	}
	

    private Category garantirCriacaoCategoria(DefaultCategoryType catEnum, UUID userID, Map<DefaultCategoryType, Category> createdCategories) {
        
        // --- Condição de Parada / Otimização ---
        // Se a categoria já foi processada e está no mapa, apenas a retorna.
        if (createdCategories.containsKey(catEnum)) {
            return createdCategories.get(catEnum);
        }

        Category fatherCat = null;
        // --- Passo Recursivo ---
        // Se a categoria atual tem um pai, garante que o pai seja criado ANTES.
        if (catEnum.getParent() != null) {
            // A função chama a si mesma para o pai.
        	fatherCat = garantirCriacaoCategoria(catEnum.getParent(), userID, createdCategories);
        }

        // --- Ação Principal ---
        // Agora que o pai (se houver) já existe, podemos criar a categoria atual.
        Category newCat = new Category();
        newCat.setName(catEnum.getDisplayName());
        newCat.setUserId(userID);
        newCat.setFatherCategory(fatherCat); // Associa o pai, que pode ser nulo (se for raiz)

        Category savedCat = categoryRepository.save(newCat);
        
        // Coloca a categoria recém-criada no mapa para que outras possam encontrá-la.
        createdCategories.put(catEnum, savedCat);

        System.out.println("Categoria criada: " + savedCat.getName() + 
                           (fatherCat != null ? " -> Pai: " + fatherCat.getName() : " (Raiz)"));

        return savedCat;
    }



}
