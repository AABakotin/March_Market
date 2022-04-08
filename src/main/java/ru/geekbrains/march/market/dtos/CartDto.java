package ru.geekbrains.march.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@NoArgsConstructor
public class CartDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private int quantity;
    private String categoryTitle;
}
