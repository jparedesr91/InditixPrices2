package com.inditex.price.domain.services.impl;

import com.inditex.price.domain.Price;
import com.inditex.price.domain.UseCase;
import com.inditex.price.domain.exceptions.NotFoundException;
import com.inditex.price.domain.exceptions.model.ErrorType;
import com.inditex.price.domain.repository.PriceRepository;
import com.inditex.price.domain.services.FindByProductQuery;
import com.inditex.price.domain.services.FindByProductResult;
import com.inditex.price.domain.services.PriceService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static com.inditex.price.domain.mapper.ApplicationMapper.MAPPER;

@UseCase
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  private final PriceRepository filteringPrices;

  @Override
  @SneakyThrows
  public FindByProductResult findByProduct(FindByProductQuery findByProductQuery) {
    Price price = filteringPrices.findByProduct(findByProductQuery.productId(),
        findByProductQuery.brandId(), findByProductQuery.applicationDate());
    if (Objects.isNull(price)) {
      throw new NotFoundException(ErrorType.RESOURCE_NOT_FOUND.toError());
    } else {
      return MAPPER.toFindByProductResult(price);
    }
  }

}
