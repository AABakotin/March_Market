package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.PageConverter;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.repositories.ProductRepository;
import ru.geekbrains.march.market.core.repositories.specifications.ProductsSpecifications;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;

//    public List<ProductDto> findAll() {
//        List<ProductDto> productDtos = new ArrayList<>();
//        for (Product product : productRepository.findAll()) {
//            productDtos.add(productConverter.entityToDto(product));
//        }
//        return productDtos;
//    }

    public ProductDto getProductDtoById(Long id) {
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
    private Page<Product> findAll(int page, int pageSize, Specification<Product> specification) {
        return productRepository.findAll(specification, PageRequest.of(page, pageSize));
    }

    public PageDto getProducts(Integer page, Integer pageSize, String titlePart, Integer minPrice, Integer maxPrice) {
        if (page < 1) {
            page = 1;
        }

        Specification<Product> spec = Specification.where(null);
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(minPrice)));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(maxPrice)));
        }
        PageDto pageDto = pageConverter.pageToDto(findAll(page - 1, pageSize, spec));
        if (page > pageDto.getTotalPages()) {
            throw new ResourceNotFoundException("Такой страницы не существует");
        }
        return pageDto;
    }
}
