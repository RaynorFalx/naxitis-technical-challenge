package com.ecommerce.backend.service;

import com.ecommerce.backend.model.product.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id:" +  id));
    }
}
