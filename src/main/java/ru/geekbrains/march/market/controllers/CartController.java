package ru.geekbrains.march.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.services.CartService;
import ru.geekbrains.march.market.utils.CartItem;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;


    @GetMapping("")
    public List<CartDto> CartShowAllProducts() {
        List<CartDto> cartDtos = new ArrayList<>();
        for (CartItem item : cartService.getAllProduct()){
            cartDtos.add(cartConverter.entityToDto(item));
        }
        return cartDtos;
    }

    @GetMapping("add/{id}")
    public void AddProductToCart(@PathVariable Long id) {
        cartService.CartAddProduct(id);
    }


    @GetMapping("delete/{id}")
    public void removeOneProductFromCart(@PathVariable Long id) {
        cartService.removeOneProduct(id);
    }

    @GetMapping ("delete_item/{id}")
    public void deleteItenFromCart (@PathVariable Long id){
        cartService.deleteItemFromCart(id);
    }


}
