package ru.geekbrains.march.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.core.entities.Product;

@Component
@RequiredArgsConstructor
public class PageConverter {

    private final ProductConverter productConverter;

    public PageDto pageToDto(Page<Product> page) {
        PageDto pageDto = new PageDto();
        pageDto.setContent(productConverter.entityToListProduct(page.getContent()));
        pageDto.setNumber(page.getNumber());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;

    }
}
