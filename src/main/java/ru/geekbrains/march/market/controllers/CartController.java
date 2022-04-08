package ru.geekbrains.march.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping("")
    public List<Product> CartShowAllProducts() {
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


}
