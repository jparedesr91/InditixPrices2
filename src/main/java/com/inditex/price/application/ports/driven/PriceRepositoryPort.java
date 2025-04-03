package com.inditex.price.application.ports.driven;

import com.inditex.price.domain.model.Price;
import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    Price findByProduct(Long productId, Long brandId, LocalDateTime applicationDate);
}
