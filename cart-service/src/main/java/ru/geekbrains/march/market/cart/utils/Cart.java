package ru.geekbrains.march.market.cart.utils;


import lombok.Data;
import ru.geekbrains.march.market.api.ProductDto;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public void add(ProductDto p) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(p.getId())) {
                items.get(i).incrementQuantity();
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void removeOneItem(Long productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(productId)) {
                items.get(i).decrementQuantity();
                if (items.get(i).getQuantity() == 0) {
                    deleteItemFromCart(productId);
                }
            }
        }
        recalculate();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void deleteItemFromCart(Long productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(productId)) {
                items.remove(i);
            }
        }
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }


}