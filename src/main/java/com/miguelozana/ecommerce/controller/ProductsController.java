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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelozana.ecommerce.dto.ProductsDTO;
import com.miguelozana.ecommerce.models.Products;
import com.miguelozana.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductsController {
    // Add methods for CRUD operations on users
    // Use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping annotations for HTTP requests
    // Implement business logic for each method to interact with the UserService
    // Return appropriate HTTP status codes and responses
    // Use DTOs (Data Transfer Objects) to transfer data between the controller and the service
    // Add error handling for invalid requests and database errors
    // Use Spring Security for authentication and authorization
    // Implement pagination and sorting for listing users

    @Autowired
    private ProductService productService;

    // All GET stuff for users
    @GetMapping
    public List<ProductsDTO> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        return products.stream()
                .map(ProductsDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Products getProductsById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category")
    public List<Products> getProductsByCategory(@RequestParam String category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/price-range")
    public List<Products> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be higher than max price");
        }
        return productService.findProductsByPriceRange(minPrice, maxPrice);
    }

    // Stuff that can only be accessed by adm
    @PostMapping
    public ProductsDTO createProducts(@RequestBody ProductsDTO products) {
        return productService.createProducts(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProducts(@PathVariable Long id, @RequestBody ProductsDTO productsDetails) {
        ProductsDTO productsUpdate = productService.updateProducts(id, productsDetails);
        return ResponseEntity.ok(productsUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
