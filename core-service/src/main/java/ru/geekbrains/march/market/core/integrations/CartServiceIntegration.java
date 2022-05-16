package ru.geekbrains.march.market.core.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.march.market.api.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getProductsCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearCart(){
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve();
    }

//    private final RestTemplate restTemplate;
//
//    public CartDto getProductsCart() {
//        return restTemplate.getForObject("http://localhost:8190/market-cart/api/v1/cart", CartDto.class);
//    }
//
//
//    public void clearCart() {
//        restTemplate.headForHeaders("http://localhost:8190/market-cart/api/v1/cart/clear");
//    }
}
