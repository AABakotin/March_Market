package ru.geekbrains.march.market.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.utils.CartItem;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
@Data
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public List<CartItem> getAllProduct() {
        return cart.getItems();
    }

    public void CartAddProduct(Long productId) {
        Product product = findFromDataBase(productId);
        cart.add(product);
    }

    public void clearCart() {
        cart.clear();
    }


    public void deleteItemFromCart (Long id){
        cart.deleteItemFromCart(id);
    }

    private Product findFromDataBase(Long productId) {
        return productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
    }


    public void removeOneProduct(Long productId) {
        cart.removeOneItem(productId);

    }
}
