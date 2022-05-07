package ru.geekbrains.march.market.cart.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter{

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems()
                .stream()
                .map(cartItemConverter::entityToDtoItems)
                .collect(Collectors.toList()),
                c.getTotalPrice());
    }


}