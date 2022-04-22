package ru.geekbrains.march.market.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.services.OrderService;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal) {
        String username = principal.getName();
        orderService.createOrder(username);

    }


    @GetMapping
    public List<OrderDto> getAllOrderItems(Principal principal) {
        String username = principal.getName();
        return orderService.getAllOrderItems(username);
    }
}