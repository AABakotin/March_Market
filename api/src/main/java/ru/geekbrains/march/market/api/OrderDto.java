package ru.geekbrains.march.market.api;




import java.math.BigDecimal;
import java.util.List;


public class OrderDto {
    private Long id;
    private List<OrderItemDto> list;
    private BigDecimal totalPrice;
    private String address;
    private String phoneNumber;


    public OrderDto() {
    }

    public OrderDto(Long id, List<OrderItemDto> list, BigDecimal totalPrice, String address, String phoneNumber) {
        this.id = id;
        this.list = list;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getList() {
        return list;
    }

    public void setList(List<OrderItemDto> list) {
        this.list = list;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}