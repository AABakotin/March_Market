package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.converters.ProductConverter;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public List<ProductDto> findAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productDtos.add(productConverter.entityToDto(product));
        }
        return productDtos;
    }

    public ProductDto showConvertCartItemById(Long id) {
        return productConverter.entityToDto(findById(id));
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: "
                + id +
                " не найден"));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(
                categoryService.findIdByTitle(
                                productDto.getCategoryTitle())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Категория с названием: "
                                        + productDto.getCategoryTitle() +
                                        " не найдена")));
        productRepository.save(product);
    }
}
