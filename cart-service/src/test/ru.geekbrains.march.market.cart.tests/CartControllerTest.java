package ru.geekbrains.march.market.cart.tests;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void addProductCartTest() throws Exception {
        Mockito.doReturn(
                        new ProductDto(1L, "Хлеб", BigDecimal.valueOf(32.00), "Еда"))
                .when(productServiceIntegration)
                .findById(1L);

        mvc.perform(
                get("/api/v1/cart/63c88786-0d7a-4731-9bfa-cee9dd7cbffb/add/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        mvc.perform(get("/api/v1/cart/63c88786-0d7a-4731-9bfa-cee9dd7cbffb/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items", hasSize(1)));

        mvc.perform(get("/api/v1/cart/63c88786-0d7a-4731-9bfa-cee9dd7cbffb/remove/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
