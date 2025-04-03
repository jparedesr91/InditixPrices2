package com.inditex.price.infrastructure.driven.database.adapters;

import static com.inditex.price.infrastructure.driven.database.mapper.PersistenceMapper.MAPPER;
import com.inditex.price.application.ports.driven.PriceRepositoryPort;
import com.inditex.price.domain.model.Price;
import java.time.LocalDateTime;
import com.inditex.price.infrastructure.driven.database.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepositoryPort {

  private final PriceRepository priceRepository;

  @Override
  public Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate) {
    return MAPPER.toPrice(
        priceRepository.findByProductIdBrandIdAndApplicationDate(productId, brandId,
            applicationDate));
  }

}