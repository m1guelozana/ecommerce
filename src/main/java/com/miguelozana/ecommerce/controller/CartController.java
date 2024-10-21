package com.miguelozana.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(cart != null) {
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCartByUser(@PathVariable Long userId) {
        ShoppingCart cart = cartService.createCartForUser(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}/delete")
    public ResponseEntity<ShoppingCart> removeItemFromCart(@PathVariable Long cartId, @RequestParam Long cartItemId) {
        ShoppingCart cart = cartService.removeItemFromCart(cartId, cartItemId);

        return ResponseEntity.ok(cart);
    }
}
