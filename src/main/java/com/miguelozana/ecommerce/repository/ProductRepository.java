package com.miguelozana.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguelozana.ecommerce.models.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByName(String productName);

    List<Products> findByCategory(String category);

    List<Products> findByPriceBetween(double minPrice, double maxPrice);

}
