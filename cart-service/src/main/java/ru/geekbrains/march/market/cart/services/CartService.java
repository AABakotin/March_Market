package ru.geekbrains.march.market.cart.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
@Data
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final CartConverter cartConverter;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();

    }

    public CartDto getCurrentCartDto(String cartId) {
       return cartConverter.entityToDto(getCurrentCart(cartId));
    }

    public Cart getCurrentCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            Cart cart = new Cart();
            carts.put(cartId, cart);
        }
        return carts.get(cartId);
    }

    public void cartAddProduct(String cartId, Long productId) {
        getCurrentCart(cartId).add(productServiceIntegration.findById(productId));
    }

    public void clearCart(String cartId) {
        getCurrentCart(cartId).clear();
    }

    public void deleteItemFromCart(String cartId, Long productId) {
        getCurrentCart(cartId).deleteItemFromCart(productId);
    }


    public BigDecimal totalPrice(String cartId) {
        return getCurrentCart(cartId).getTotalPrice();
    }

    public void removeOneProductById(String cartId, Long productId) {
        getCurrentCart(cartId).removeOneItem(productId);
    }
}
