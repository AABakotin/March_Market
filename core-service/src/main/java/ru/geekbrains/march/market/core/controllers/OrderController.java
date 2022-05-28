package ru.geekbrains.march.market.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username, @RequestBody OrderDto orderDto) {
        orderService.createOrder(username, orderDto.getAddress(), orderDto.getPhoneNumber());
    }

    @GetMapping
    public List<OrderDto> getOrder(@RequestHeader String username) {
        return orderService.getOrder(username);
    }
}