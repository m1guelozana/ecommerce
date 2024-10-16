package com.miguelozana.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelozana.ecommerce.dto.CategoryDTO;
import com.miguelozana.ecommerce.models.Category;
import com.miguelozana.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParentCategoryId() != null) {

            Category parentCategory = categoryService.getCategoryById(categoryDTO.getParentCategoryId());
            if (parentCategory != null) {
                category.setParentCategory(parentCategory);
            }
        }

        Category savedCategory = categoryService.createCategory(category);

        return ResponseEntity.ok(new CategoryDTO(savedCategory));
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryService.updateCategory(id, categoryDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
