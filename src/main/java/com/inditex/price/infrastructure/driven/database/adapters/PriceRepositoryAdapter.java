package com.inditex.price.infrastructure.driven.database.adapters;

import static com.inditex.price.infrastructure.driven.database.mappers.PersistenceMapper.MAPPER;
import com.inditex.price.application.ports.driven.PriceRepositoryPort;
import com.inditex.price.domain.model.Price;
import java.time.LocalDateTime;
import com.inditex.price.infrastructure.driven.database.repositories.PriceJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceJpaRepository priceRepository;

  @Override
  public Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate) {
    return MAPPER.toPrice(
        priceRepository.findByProductIdBrandIdAndApplicationDate(productId, brandId,
            applicationDate));
  }

}