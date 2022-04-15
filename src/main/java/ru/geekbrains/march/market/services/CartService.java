package ru.geekbrains.march.market.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.utils.CartItem;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@Data
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartConverter cartConverter;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public List<CartDto> getAllProduct() {
        List<CartDto> cartDtos = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            cartDtos.add(cartConverter.entityToDto(item));
        }
        return cartDtos;
    }

    public void CartAddProduct(Long productId) {
        Product product = findFromDataBase(productId);
        cart.add(product);
    }

    public void clearCart() {
        cart.clear();
    }


    public void deleteItemFromCart(Long id) {
        cart.deleteItemFromCart(id);
    }

    private Product findFromDataBase(Long productId) {
        return productService.findById(productId);
    }


    public BigDecimal totalPrice() {
        return cart.getTotalPrice();
    }


    public void removeOneProduct(Long productId) {
        cart.removeOneItem(productId);

    }
}
