package ru.geekbrains.march.market.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;


    public void incrementQuantity() {
        quantity++;
        price = price.add(pricePerProduct);
    }

    public void decrementQuantity() {
        quantity--;
        price = price.subtract(pricePerProduct);
    }
}