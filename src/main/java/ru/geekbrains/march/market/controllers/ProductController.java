package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.converters.ProductConverter;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List <ProductDto> productDtos = new ArrayList<>();
        for (Product product : productService.findAll()) {
              productDtos.add(productConverter.entityToDto(product));
        }
        return productDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productConverter.entityToDto(productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: "
                        + id +
                        " не найден")));
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
