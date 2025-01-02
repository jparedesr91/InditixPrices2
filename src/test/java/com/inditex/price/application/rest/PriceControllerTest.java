package com.inditex.price.application.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.price.application.utils.MessageUtil;
import com.inditex.price.domain.services.FindByProductResult;
import com.inditex.price.domain.services.PriceService;
import java.time.LocalDateTime;
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
    private PriceService priceService;

    @MockitoBean
    private MessageUtil messageUtil;

    @Test
    void testFindByProduct() throws Exception {
        when(priceService.findByProduct(any())).thenReturn(getFindByProductResult());
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
