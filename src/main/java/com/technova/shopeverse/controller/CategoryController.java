package com.technova.shopeverse.controller;
import com.technova.shopeverse.dto.CategoryDTO;
import com.technova.shopeverse.model.Category;
import com.technova.shopeverse.service.CategoryService; // Asumo que tienes un CategoryService
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Método para convertir una entidad a un DTO
    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    // Método para convertir un DTO a una entidad
    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            List<CategoryDTO> categoryDTOs = categories.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(categoryDTOs); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(convertToDTO(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category created = categoryService.createCategory(category);
        return ResponseEntity.status(201).body(convertToDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = convertToEntity(categoryDTO);
            Category updated = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(convertToDTO(updated)); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
