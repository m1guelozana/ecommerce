package com.miguelozana.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.models.CartItem;
import com.miguelozana.ecommerce.models.Products;
import com.miguelozana.ecommerce.models.ShoppingCart;
import com.miguelozana.ecommerce.repository.ProductRepository;
import com.miguelozana.ecommerce.repository.ShoppingCartRepository;

@Service
public class CartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    public ShoppingCart addItemToCart(Long cartId, Long productId, int quantity) {
        Optional<ShoppingCart> shOptional = shoppingCartRepository.findById(cartId);
        Optional<Products> ptOptional = productRepository.findById(productId);

        if (shOptional.isPresent() && ptOptional.isPresent()) {
            ShoppingCart cart = shOptional.get();
            Products product = ptOptional.get();

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(Double.valueOf(product.getPrice() * quantity));
            cartItem.setShoppingCart(cart);

            cart.getItems().add(cartItem);
            updateCartTotal(cart);

            return shoppingCartRepository.save(cart);
        }
        throw new IllegalArgumentException("Carrinho ou produto não encontrado!");
    }

    public ShoppingCart removeItemFromCart(Long cartId, Long cartItemId) {
        Optional<ShoppingCart> shOptional = shoppingCartRepository.findById(cartId);

        if (shOptional.isPresent()) {
            ShoppingCart cart = shOptional.get();
            cart.getItems().removeIf(cartItem -> cartItem.getId().equals(cartItemId));
            updateCartTotal(cart);
            return shoppingCartRepository.save(cart);
        }

        throw new IllegalArgumentException("Carrinho não encontrado!");
    }

    private void updateCartTotal(ShoppingCart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalPrice(total);
    }

}
