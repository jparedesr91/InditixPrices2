package com.inditex.price.domain.services;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.inditex.price.domain.validation.Validation.validate;

public record FindByProductResult(@NotNull Long productId, @NotNull Long brandId,
                                  @NotNull LocalDateTime startDate, @NotNull LocalDateTime endDate,
                                  @NotNull Double price) {

  public FindByProductResult(Long productId, Long brandId, LocalDateTime startDate,
      LocalDateTime endDate, Double price) {
    this.productId = productId;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    validate(this);
  }

}
