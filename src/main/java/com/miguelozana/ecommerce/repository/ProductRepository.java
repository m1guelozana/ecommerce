package com.miguelozana.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miguelozana.ecommerce.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByName(String productName);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.name = :categoryName")
    List<Products> findByCategory(String categoryName);

    List<Products> findByPriceBetween(double minPrice, double maxPrice);

}
