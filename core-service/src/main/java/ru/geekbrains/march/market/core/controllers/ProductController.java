package ru.geekbrains.march.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.PageConverter;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.exceptions.AppError;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.repositories.specifications.ProductsSpecifications;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Продукты", description = "Методы работы с продуктами")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public PageDto getAllProducts(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "page_size", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "title_part", required = false) String titlePart,
                                  @RequestParam(name = "min_price", required = false) Integer minPrice,
                                  @RequestParam(name = "max_price", required = false) Integer maxPrice) {

        return productService.getProducts(page, pageSize, titlePart, minPrice, maxPrice);
    }

    @Operation(
            summary = "Запрос продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductDtoById(id);
    }

    @Operation(
            summary = "Запрос на удаление продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

}

