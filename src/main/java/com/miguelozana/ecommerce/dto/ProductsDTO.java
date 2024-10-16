package com.miguelozana.ecommerce.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.miguelozana.ecommerce.models.Category;
import com.miguelozana.ecommerce.models.Products;

import lombok.Data;

@Data
public class ProductsDTO {

    private Long id;
    private String name;
    private Float price;
    private String description;
    private List<Long> categoryIds;
    private List<String> categoryNames;

    public ProductsDTO() {
    }

    public ProductsDTO(Products product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        if (product.getCategories() != null) {
            this.categoryIds = product.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());

            this.categoryNames = product.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
        }
    }
}
