package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;


@Component
public class OrderConverter {

    public OrderDto entityToDto(Order item) {

        OrderDto o = new OrderDto();

        for (OrderItem c : item.getItems()) {
            o.setNameProduct(c.getProduct().getTitle());
            o.setPrice(c.getPrice());
            o.setQuantity(c.getQuantity());
            o.setPricePerProduct(c.getPricePerProduct());

        }
        return o;
    }

}
