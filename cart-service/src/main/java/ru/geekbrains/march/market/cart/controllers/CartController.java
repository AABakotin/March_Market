package ru.geekbrains.march.market.cart.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{guestCartId}")
    public CartDto getAllProductsInCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartService.getCurrentCartDto(currentCartId);
    }

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.cartAddProduct(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/remove/{productId}")
    public void removeOneProductFromCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.removeOneProductById(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/delete_item/{productId}")
    public void deleteItemFromCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.deleteItemFromCart(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

//    @GetMapping("/{guestCartId}/total")
//    public BigDecimal totalPrice(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
//        String currentCartId = selectCartId(username, guestCartId);
//        return cartService.totalPrice(currentCartId);
//    }

    @GetMapping("/{guestCartId}/merge")
    public void mergeCarts (@RequestHeader String username, @PathVariable String guestCartId){
        cartService.mergeCart(guestCartId, username);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }

}
