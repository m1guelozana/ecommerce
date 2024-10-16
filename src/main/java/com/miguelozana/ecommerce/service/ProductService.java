package com.miguelozana.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.dto.ProductsDTO;
import com.miguelozana.ecommerce.models.Category;
import com.miguelozana.ecommerce.models.Products;
import com.miguelozana.ecommerce.repository.CategoryRepository;
import com.miguelozana.ecommerce.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // All that users see on their interface. Simple, no? Yeah... maybe lol.
    // Just keep that in mind, only for user interface.
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found!"));
    }

    public List<Products> findProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Products findByName(String productName) {
        return productRepository.findByName(productName).orElseThrow(() -> new IllegalArgumentException("Product Not Found!"));
    }

    public List<Products> findProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be different from max price");
        }
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // All that users cannot see on their interface. Only admin
    // Simple CRUD
    // For front-end it will be necessary to build an adm pannel. GLHF!
    public ProductsDTO createProducts(ProductsDTO productsDTO) {
        Products product = new Products();
        product.setName(productsDTO.getName());
        product.setPrice(productsDTO.getPrice());
        product.setDescription(productsDTO.getDescription());
    
        if (productsDTO.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(productsDTO.getCategoryIds());
            product.setCategories(categories);
        }
    
        Products savedProduct = productRepository.save(product);
        
        ProductsDTO savedProductDTO = new ProductsDTO(savedProduct);
        
        return savedProductDTO;
    }

    @Transactional
    public ProductsDTO updateProducts(Long id, ProductsDTO productsDTO) {
        Products product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found!"));

        product.setName(productsDTO.getName());
        product.setPrice(productsDTO.getPrice());
        product.setDescription(productsDTO.getDescription());

        if (productsDTO.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(productsDTO.getCategoryIds());
            product.setCategories(categories);
        }

        Products updatedProduct = productRepository.save(product);
        return new ProductsDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }

        productRepository.deleteById(id);
    }
}
