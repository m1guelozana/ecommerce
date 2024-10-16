package com.miguelozana.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelozana.ecommerce.models.ShoppingCart;
import com.miguelozana.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add")
    public ResponseEntity<ShoppingCart> addItemToCart(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam Integer quantity) {
        ShoppingCart cart = cartService.addItemToCart(cartId, productId, quantity);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}/delete")
    public ResponseEntity<ShoppingCart> removeItemFromCart(@PathVariable Long cartId, @RequestParam Long cartItemId) {
        ShoppingCart cart = cartService.removeItemFromCart(cartId, cartItemId);

        return ResponseEntity.ok(cart);
    }
}
