package com.inditex.price.infrastructure.repository.mongo;

import static com.inditex.price.infrastructure.repository.mongo.PersistenceMapper.MAPPER;

import com.inditex.price.application.utils.PersistenceAdapter;
import com.inditex.price.domain.Price;
import com.inditex.price.domain.repository.PriceRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

  private final JpaPriceRepository priceRepository;

  @Override
  public Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate) {
    return MAPPER.toPrice(
        priceRepository.findByProductIdBrandIdAndApplicationDate(productId, brandId,
            applicationDate));
  }

}