package ru.geekbrains.march.market.converters;


import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.utils.CartItem;

@Component
public class CartConverter {

    public CartDto entityToDto(CartItem item) {

        CartDto cartDto = new CartDto();
        cartDto.setId(item.getProductId());
        cartDto.setTitle(item.getProductTitle());
        cartDto.setCategoryTitle(item.getProductTitle());
        cartDto.setPrice(item.getPrice());
        cartDto.setQuantity(item.getQuantity());

        return cartDto;
    }
}
