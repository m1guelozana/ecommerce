package com.miguelozana.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguelozana.ecommerce.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
