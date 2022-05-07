package ru.geekbrains.march.market.cart.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;
    @MockBean
    private ProductServiceIntegration productServiceIntegration;


    @Test
    public void AllFunctionCartTest() {

        Mockito.doReturn(
                new ProductDto(1L, "Orange", BigDecimal.valueOf(100), "Food"))
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(
                new ProductDto(2L, "Apple", BigDecimal.valueOf(100), "Food"))
                .when(productServiceIntegration)
                .findById(2L);
        Mockito.doReturn(
                new ProductDto(3L, "Mango", BigDecimal.valueOf(100), "Food"))
                .when(productServiceIntegration)
                .findById(3L);
        Mockito.doReturn(
                new ProductDto(4L, "NoteBook", BigDecimal.valueOf(100), "Electronic"))
                .when(productServiceIntegration)
                .findById(4L);
        Mockito.doReturn(
                new ProductDto(5L, "XBox", BigDecimal.valueOf(100), "Electronic"))
                .when(productServiceIntegration)
                .findById(5L);

        cartService.cartAddProduct(1L);
        cartService.cartAddProduct(2L);
        cartService.cartAddProduct(2L);
        cartService.cartAddProduct(3L);
        cartService.cartAddProduct(3L);
        cartService.cartAddProduct(3L);
        cartService.cartAddProduct(4L);
        cartService.cartAddProduct(5L);
        cartService.cartAddProduct(5L);
        cartService.cartAddProduct(5L);
        Assertions.assertEquals(BigDecimal.valueOf(1000), cartService.getCart().getTotalPrice());
        Assertions.assertEquals(5, cartService.getAllProduct().getItems().size());

        cartService.removeOneProduct(1L);
        Assertions.assertEquals(BigDecimal.valueOf(900), cartService.getCart().getTotalPrice());
        Assertions.assertEquals(4, cartService.getAllProduct().getItems().size());
        Assertions.assertEquals(3, cartService.getAllProduct().getItems().get(3).getQuantity());

        cartService.deleteItemFromCart(5L);
        Assertions.assertEquals(BigDecimal.valueOf(600), cartService.getCart().getTotalPrice());
        Assertions.assertEquals(3, cartService.getAllProduct().getItems().size());
        Assertions.assertEquals(3, cartService.getAllProduct().getItems().get(1).getQuantity());

        cartService.clearCart();
        Assertions.assertEquals(BigDecimal.valueOf(0), cartService.getCart().getTotalPrice());
        Assertions.assertEquals(0, cartService.getAllProduct().getItems().size());
    }
}
