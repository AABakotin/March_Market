package ru.geekbrains.march.market.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppError {
    private String code;
    private String message;
}
