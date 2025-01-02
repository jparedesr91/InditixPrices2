package com.inditex.price.domain.services;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.inditex.price.domain.validation.Validation.validate;

public record FindByProductQuery(@NotNull Long productId, @NotNull Long brandId,
                                 @NotNull LocalDateTime applicationDate) {

  public FindByProductQuery(Long productId, Long brandId, LocalDateTime applicationDate) {
    this.productId = productId;
    this.brandId = brandId;
    this.applicationDate = applicationDate;
    validate(this);
  }

}
