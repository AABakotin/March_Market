package ru.geekbrains.march.market.core.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.geekbrains.march.market.core.entities.Category;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Category> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Еда");
        category.setProducts(Collections.emptyList());
        assertThat(jackson.write(category))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("Еда");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"Еда\",\"products\": []}";
        Category expectedCategory = new Category();
        expectedCategory.setId(2L);
        expectedCategory.setTitle("Еда");
        expectedCategory.setProducts(Collections.emptyList());

        assertThat(jackson.parse(content)).isEqualTo(expectedCategory);
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("Еда");
    }
}