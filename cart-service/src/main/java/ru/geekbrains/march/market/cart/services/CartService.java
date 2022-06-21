package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final CartConverter cartConverter;
    private final RedisTemplate<String, Object> redisTemplate;



    public CartDto getCurrentCartDto(String cartId) {
        Cart cart = getCurrentCart(cartId);
        return cartConverter.entityToDto(cart);
    }

    private Cart getCurrentCart(String cartId) {
        if (!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }
        return (Cart)redisTemplate.opsForValue().get(cartId);


    }

    public void cartAddProduct(String cartId, Long productId) {
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            cart.add(p);
        });
    }

    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    public void deleteItemFromCart(String cartId, Long productId) {
        execute(cartId, cart -> cart.deleteItemFromCart(productId));
    }


    public void removeOneProductById(String cartId, Long productId) {
        execute(cartId, cart -> cart.removeOneItem(productId));
    }

    public void mergeCart(String cartId, String username) {
            redisTemplate.opsForValue().set(username, getCurrentCart(cartId));
            redisTemplate.delete(cartId);
    }

    private void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }

}
