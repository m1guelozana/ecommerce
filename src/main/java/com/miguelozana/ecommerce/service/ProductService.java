package com.miguelozana.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.models.Products;
import com.miguelozana.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // All that users see on their interface. Simple, no? Yeah... maybe lol.
    // Just keep that in mind, only for user interface.
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Products> findProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Products findByName(String productName) {
        return productRepository.findByName(productName).orElseThrow(() -> new IllegalArgumentException("Product Not Found!"));
    }

    public List<Products> findProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("O preço mínimo não pode ser maior que o preço máximo.");
        }
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // All that users cannot see on their interface. Only admin
    // For front-end it will be necessary to build an adm pannel. GLHF! 
    public Products createProducts(Products products) {
        
        return null;
    }
}
