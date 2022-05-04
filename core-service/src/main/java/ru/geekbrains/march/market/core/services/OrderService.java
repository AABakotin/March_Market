package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.converters.OrderConverter;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Transactional
    public void createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getProductsCart();
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        for (CartItemDto o : cartDto.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productService.findById(o.getProductId()));
            orderItem.setPrice(o.getPrice());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setQuantity(o.getQuantity());
            orderItemList.add(orderItem);
        }
        order.setItems(orderItemList);
        orderRepository.save(order);
        cartServiceIntegration.clearCart();
    }

    public List<OrderDto> getAllOrderItems(String username) {
        List<Order> orderList = orderRepository.findAllByUsername(username);
       return orderList.stream().map(orderConverter::entityToDto).collect(Collectors.toList());
        }
    }

