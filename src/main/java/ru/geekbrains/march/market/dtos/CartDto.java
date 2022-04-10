package ru.geekbrains.march.market.dtos;

import lombok.Data;

import java.math.BigDecimal;



@Data
public class CartDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private int quantity;
    private String categoryTitle;

}
