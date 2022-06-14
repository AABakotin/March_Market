package ru.geekbrains.march.market.cart.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Корзины", description = "Методы работы с корзинами")
public class CartController {
    private final CartService cartService;


    @Operation(summary = "Запрос списка товаров в корзине",
            responses = {
                    @ApiResponse(
                            description = "Список товаров в корзине получен", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class)))
            })
    @GetMapping("/{guestCartId}")
    public CartDto getAllProductsInCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartService.getCurrentCartDto(currentCartId);
    }

    @Operation(summary = "Запрос на получение гостевой корзины")
    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.cartAddProduct(currentCartId, productId);
    }

    @Operation(summary = "Добавление товара в корзину",
            responses = {
                    @ApiResponse(description = "Товар добавлен в корзину", responseCode = "200")
            })
    @GetMapping("/{guestCartId}/remove/{productId}")
    public void removeOneProductFromCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.removeOneProductById(currentCartId, productId);
    }

    @Operation(summary = "Удаление по штучно товара из корзины",
            responses = {
                    @ApiResponse(description = "Товар удален", responseCode = "200")
            })
    @GetMapping("/{guestCartId}/delete_item/{productId}")
    public void deleteItemFromCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.deleteItemFromCart(currentCartId, productId);
    }

    @Operation(summary = "Удаление всех товаров в корзине",
            responses = {
                    @ApiResponse(description = "Корзина очищена", responseCode = "200")
            })
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

    @Operation(summary = "Объединение гостевой корзины и корзины зарегистрированного пользователя")
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
