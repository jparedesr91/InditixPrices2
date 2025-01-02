package com.inditex.price.domain.repository;

import com.inditex.price.domain.Price;
import java.time.LocalDateTime;

public interface PriceRepository {

  Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate);

}
