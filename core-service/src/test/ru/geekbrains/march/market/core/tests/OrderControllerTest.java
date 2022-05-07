package ru.geekbrains.march.market.core.tests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void createOrder() throws Exception {
        mvc.perform(post("/api/v1/order").contentType(MediaType.APPLICATION_JSON).header("bob"))
                .andExpect(status().isCreated());
    }


    @Test
    public void getAllOrderByUser() throws Exception {
        mvc.perform(
                        get
                                ("/api/v1/order").contentType(MediaType.APPLICATION_JSON).header("bob"))
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }
}
