package com.inditex.price.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import com.inditex.price.domain.model.Brand;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.Product;
import com.inditex.price.application.ports.driving.FindByProductResult;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ApplicationMapperTest {

  private final ApplicationMapper mapper = Mappers.getMapper(ApplicationMapper.class);

  @Test
  void testMappingFromPriceToFindByProductResult() {
    Price price = getPrice();
    FindByProductResult result = mapper.toFindByProductResult(price);
    assertNotNull("FindByProductResult should not be null", result);
    assertEquals(35455L, result.productId());
    assertEquals(1L, result.brandId());
    assertEquals(LocalDateTime.of(2022, 1, 1, 12, 0, 0), result.startDate());
    assertEquals(LocalDateTime.of(2022, 12, 31, 12, 0, 0), result.endDate());
    assertEquals(35.50, result.price());
  }

  private Price getPrice() {
    Price price = new Price();
    price.setId(0L);
    price.setBrand(new Brand(1L, "ZARA"));
    price.setProduct(new Product(35455L, "SHOES"));
    price.setStartDate(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
    price.setEndDate(LocalDateTime.of(2022, 12, 31, 12, 0, 0));
    price.setPrice(35.50);
    price.setCurrency("EUR");
    price.setPriority(0);
    return price;
  }

}
