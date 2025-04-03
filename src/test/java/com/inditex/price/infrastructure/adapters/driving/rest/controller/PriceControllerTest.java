package com.inditex.price.infrastructure.adapters.driving.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.price.application.ports.driving.ForFilteringPricesPort;
import com.inditex.price.application.ports.driving.FindByProductResult;

import java.time.LocalDateTime;

import com.inditex.price.infrastructure.driving.rest.adapters.PriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ForFilteringPricesPort forFilteringPrices;

    @Test
    void testFindByProduct() throws Exception {
        when(forFilteringPrices.findByProduct(any())).thenReturn(getFindByProductResult());
        String jsonStr = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-14T17:00:00"
                }
                """;
        mockMvc.perform(post("/filter")
                        .content(jsonStr)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    private FindByProductResult getFindByProductResult() {
        return new FindByProductResult
                (35455L,
                        1L,
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        35.50
                );
    }
}
