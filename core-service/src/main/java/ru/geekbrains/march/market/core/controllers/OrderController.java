package ru.geekbrains.march.market.core.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {
    private final OrderService orderService;


    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username, @RequestBody OrderDto orderDto) {
        orderService.createOrder(username, orderDto.getAddress(), orderDto.getPhoneNumber());
    }

    @Operation(summary = "Получение всех заказов по пользователю")
    @GetMapping
    public List<OrderDto> getOrder(@RequestHeader String username) {
        return orderService.getOrder(username);
    }
}