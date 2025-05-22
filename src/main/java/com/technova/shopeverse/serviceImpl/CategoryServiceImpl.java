package com.technova.shopeverse.serviceImpl;

import com.technova.shopeverse.dto.CategoryDTO;
import com.technova.shopeverse.model.Category;
import com.technova.shopeverse.repository.CategoryRepository;
import com.technova.shopeverse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        List<String> productNames = category.getProducts().stream()
                .map(product -> product.getName())
                .toList();

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                productNames
        );
    }
}
