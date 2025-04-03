package com.inditex.price.infrastructure.adapters.driven.database.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.inditex.price.domain.model.Brand;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.Product;

import java.time.LocalDateTime;

import com.inditex.price.infrastructure.driven.database.entities.BrandEntity;
import com.inditex.price.infrastructure.driven.database.entities.PriceEntity;
import com.inditex.price.infrastructure.driven.database.entities.ProductEntity;
import com.inditex.price.infrastructure.driven.database.repositories.PriceRepository;
import com.inditex.price.infrastructure.driven.database.adapters.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @Mock
    private PriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    @Test
    void Given_Prices_When_GetByProduct_Then_ReturnPrice() {
        when(jpaPriceRepository.findByProductIdBrandIdAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(getPriceEntity());
        Price expected = getPrice();
        Price result = priceRepository.findByProduct(35455L, 1L, LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        assertEquals(expected,result);
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

    private PriceEntity getPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(0L);
        priceEntity.setBrand(new BrandEntity(1L, "ZARA"));
        priceEntity.setProduct(new ProductEntity(35455L, "SHOES"));
        priceEntity.setStartDate(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2022, 12, 31, 12, 0, 0));
        priceEntity.setPrice(35.50);
        priceEntity.setCurrency("EUR");
        priceEntity.setPriority(0);
        return priceEntity;
    }
}
