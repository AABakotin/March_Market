package ru.geekbrains.march.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.services.CartService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @GetMapping("")
    public List<CartDto> ShowAllProductsinCart() {
        return cartService.getAllProduct();
    }

    @GetMapping("add/{id}")
    public void AddProductToCart(@PathVariable Long id) {
        cartService.CartAddProduct(id);
    }


    @GetMapping("delete/{id}")
    public void removeOneProductFromCart(@PathVariable Long id) {
        cartService.removeOneProduct(id);
    }


    @GetMapping("delete_item/{id}")
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
