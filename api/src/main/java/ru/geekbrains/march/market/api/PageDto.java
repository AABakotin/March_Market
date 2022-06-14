package ru.geekbrains.march.market.api;

import java.util.List;

public class PageDto {
    private List<ProductDto> content;
    private int totalPages;
    private int number;

    public List<ProductDto> getContent() {
        return content;
    }

    public void setContent(List<ProductDto> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PageDto() {
    }

    public PageDto(List<ProductDto> productDtoList, int totalPages, int currentPage) {
        this.content = productDtoList;
        this.totalPages = totalPages;
        this.number = currentPage;
    }
}
