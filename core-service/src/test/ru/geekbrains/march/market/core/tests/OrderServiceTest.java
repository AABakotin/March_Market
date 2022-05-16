package ru.geekbrains.march.market.core.tests;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {
//
//    @Autowired
//    private OrderService orderService;
//    @MockBean
//    private OrderRepository orderRepository;
//    @MockBean
//    private CartServiceIntegration cartServiceIntegration;
//    @MockBean
//    private ProductService productService;
//
//    @Test
//    public void createOrderTest() {
//        CartItemDto orange = new CartItemDto(1L, "Orange", 1, BigDecimal.valueOf(1), BigDecimal.valueOf(1));
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("Orange");
//        ArrayList<CartItemDto> cartItemDtos = new ArrayList<>();
//        cartItemDtos.add(orange);
//        CartDto cartDto = new CartDto();
//        cartDto.setItems(cartItemDtos);
//        cartDto.setTotalPrice(BigDecimal.valueOf(1));
//        Mockito.doReturn(cartDto)
//                .when(cartServiceIntegration).getProductsCart("bob");
//        Mockito.doReturn(product)
//                .when(productService).findById(1L);
//        orderService.createOrder("bob","MSC","8888888888");
//        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
//    }
//
//    @Test
//    public void getOrderByUsername() {
//        orderService.getOrder("bob");
//        Mockito.verify(orderRepository, Mockito.times(1)).findAllByUsername(ArgumentMatchers.any());
//
//    }
}
