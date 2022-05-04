package ru.geekbrains.march.market.api;




import java.math.BigDecimal;


public class OrderDto {

    private String nameProduct;
    private int quantity;
    private BigDecimal price;
    private BigDecimal PricePerProduct;

    public OrderDto() {
    }

    public OrderDto(String nameProduct, int quantity, BigDecimal price, BigDecimal pricePerProduct) {
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
        PricePerProduct = pricePerProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePerProduct() {
        return PricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        PricePerProduct = pricePerProduct;
    }
}
