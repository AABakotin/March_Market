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
                new ProductDto(1L, "Orange", BigDecimal.valueOf(100), "Еда"))
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(
                new ProductDto(2L, "Apple", BigDecimal.valueOf(100), "Еда"))
                .when(productServiceIntegration)
                .findById(2L);
        Mockito.doReturn(
                new ProductDto(3L, "Mango", BigDecimal.valueOf(100), "Еда"))
                .when(productServiceIntegration)
                .findById(3L);
        Mockito.doReturn(
                new ProductDto(4L, "NoteBook", BigDecimal.valueOf(100), "Электроника"))
                .when(productServiceIntegration)
                .findById(4L);
        Mockito.doReturn(
                new ProductDto(5L, "XBox", BigDecimal.valueOf(100), "Электроника"))
                .when(productServiceIntegration)
                .findById(5L);

        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb", 1L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",2L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",2L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",3L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",3L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",3L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",4L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",5L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",5L);
        cartService.cartAddProduct("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",5L);
        Assertions.assertEquals(BigDecimal.valueOf(1000), cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getTotalPrice());
        Assertions.assertEquals(5, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().size());

        cartService.removeOneProductById("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",1L);
        Assertions.assertEquals(BigDecimal.valueOf(900), cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getTotalPrice());
        Assertions.assertEquals(4, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().size());
        Assertions.assertEquals(3, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().get(3).getQuantity());

        cartService.deleteItemFromCart("63c88786-0d7a-4731-9bfa-cee9dd7cbffb",5L);
        Assertions.assertEquals(BigDecimal.valueOf(600), cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getTotalPrice());
        Assertions.assertEquals(3, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().size());
        Assertions.assertEquals(3, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().get(1).getQuantity());

        cartService.clearCart("63c88786-0d7a-4731-9bfa-cee9dd7cbffb");
        Assertions.assertEquals(BigDecimal.valueOf(0), cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getTotalPrice());
        Assertions.assertEquals(0, cartService.getCurrentCartDto("63c88786-0d7a-4731-9bfa-cee9dd7cbffb").getItems().size());
    }
}
