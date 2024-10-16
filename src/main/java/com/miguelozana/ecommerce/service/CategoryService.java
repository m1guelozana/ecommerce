package com.miguelozana.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.models.Category;
import com.miguelozana.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        if (category.getParentCategory() != null) {
            Long parentId = category.getParentCategory().getId();
            Category parentCategory = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent Category not found!"));
            category.setParentCategory(parentCategory);
        }
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        category.setParentCategory(categoryDetails.getParentCategory());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
