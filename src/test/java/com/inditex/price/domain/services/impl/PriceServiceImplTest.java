package com.inditex.price.domain.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.price.domain.Brand;
import com.inditex.price.domain.Price;
import com.inditex.price.domain.Product;
import com.inditex.price.domain.exceptions.NotFoundException;
import com.inditex.price.domain.repository.PriceRepository;
import com.inditex.price.domain.services.FindByProductQuery;
import com.inditex.price.domain.services.FindByProductResult;
import com.inditex.price.domain.services.PriceService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceServiceImpl priceService;

  @Test
  void Given_PricesNotFound_When_GetByProduct_Then_ReturnError() {
    when(priceRepository.findByProduct(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(
        null);
    assertThrows(NotFoundException.class, ()
        -> priceService
        .findByProduct(getFindByProductQuery()));
    verify(priceRepository).findByProduct(anyLong(), anyLong(), any(LocalDateTime.class));
  }

  @Test
  void Given_PricesFound_When_GetByProduct_Then_ReturnOk() {
    when(priceRepository.findByProduct(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(
        getPrice());
    assertEquals(getFindByProductResult(),
        priceService.findByProduct(getFindByProductQuery()));
    verify(priceRepository).findByProduct(anyLong(), anyLong(), any(LocalDateTime.class));
  }

  private FindByProductQuery getFindByProductQuery() {
    return new FindByProductQuery(
        1L,
        1L,
        LocalDateTime.of(2022, 6, 15, 6, 0, 0)
    );
  }

  private FindByProductResult getFindByProductResult() {
    return new FindByProductResult
        (35455L,
            1L,
            LocalDateTime.of(2022, 1, 1, 12, 0, 0),
            LocalDateTime.of(2022, 12, 31, 12, 0, 0),
            35.50
        );
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
