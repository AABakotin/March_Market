package ru.geekbrains.march.market.core.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.march.market.api.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final RestTemplate restTemplate;

    public CartDto getProductsCart() {
        return restTemplate.getForObject("http://localhost:8190/market-cart/api/v1/cart", CartDto.class);
    }


    public void clearCart() {
        restTemplate.headForHeaders("http://localhost:8190/market-cart/api/v1/cart/clear");
    }
}
