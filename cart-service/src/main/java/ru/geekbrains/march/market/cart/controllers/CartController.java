package ru.geekbrains.march.market.cart.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;
import java.util.List;

    ////////////////////////////
    // port: 8190 /market-cart//
    ////////////////////////////

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public CartDto getAllProductsInCart() {
        return cartService.getAllProduct();
    }

    @GetMapping("/add/{id}")
    public void AddProductToCart(@PathVariable Long id) {
        cartService.CartAddProduct(id);
    }

    @GetMapping("/delete/{id}")
    public void removeOneProductFromCart(@PathVariable Long id) {
        cartService.removeOneProduct(id);
    }

    @GetMapping("/delete_item/{id}")
    public void deleteItemFromCart(@PathVariable Long id) {
        cartService.deleteItemFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping("/total")
    public BigDecimal totalPrice() {
        return cartService.totalPrice();
    }

}