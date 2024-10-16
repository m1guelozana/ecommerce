package com.miguelozana.ecommerce.dto;

import com.miguelozana.ecommerce.models.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentCategoryId;
    private String parentCategoryName;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        if (category.getParentCategory() != null) {
            this.parentCategoryId = category.getParentCategory().getId();
            this.parentCategoryName = category.getParentCategory().getName();
        }
    }
}

