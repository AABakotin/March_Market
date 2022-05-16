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
import java.util.ArrayList;


@Service
@Data
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final CartConverter cartConverter;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public CartDto getAllProduct() {
        return cartConverter.entityToDto(cart);
    }

    public void cartAddProduct(Long productId) {
        cart.add(productServiceIntegration.findById(productId));
    }

    public void clearCart() {
        cart.clear();
    }

    public void deleteItemFromCart(Long id) {
        cart.deleteItemFromCart(id);
    }


    public BigDecimal totalPrice() {
        return cart.getTotalPrice();
    }

    public void removeOneProduct(Long productId) {
        cart.removeOneItem(productId);
    }
}
