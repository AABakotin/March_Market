package ru.geekbrains.march.market.cart.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartConverter{


    public CartDto entityToDto(Cart c, List<CartItemDto> itemDtos) {
        CartDto dto = new CartDto();
        dto.setTotalPrice(c.getTotalPrice());
        dto.setItems(itemDtos);
        return dto;
    }


}