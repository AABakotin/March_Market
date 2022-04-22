package ru.geekbrains.march.market.cart.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.CartItem;

import java.util.List;

@Component
public class CartItemConverter {

    public CartItemDto entityToDtoItems(CartItem item) {

        return new CartItemDto(
                item.getProductId(),
                item.getProductTitle(),
                item.getQuantity(),
                item.getPricePerProduct(),
                item.getPrice()
        );
    }

    public CartItemDto entityToDtoItems(List<CartItem> item) {

        CartItemDto cartItemDto = new CartItemDto();
        for (CartItem c : item) {
            cartItemDto.setProductId(c.getProductId());
            cartItemDto.setPrice(c.getPrice());
            cartItemDto.setProductTitle(c.getProductTitle());
            cartItemDto.setQuantity(c.getQuantity());
            cartItemDto.setPricePerProduct(c.getPricePerProduct());
        }
        return cartItemDto;
    }
}
