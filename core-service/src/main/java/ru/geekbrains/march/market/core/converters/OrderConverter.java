package ru.geekbrains.march.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.entities.Order;


import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    public OrderDto entityToDto(Order item) {
        return new OrderDto(item.getId(),
                item.getItems()
                        .stream()
                        .map(orderItemConverter::entityToDto)
                        .collect(Collectors.toList()),
                item.getTotalPrice());
    }

}
