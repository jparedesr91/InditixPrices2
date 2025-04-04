package com.inditex.price.application.usecases;

import com.inditex.price.application.ports.driven.PriceRepositoryPort;
import com.inditex.price.application.ports.driving.ForFilteringPricesPort;
import com.inditex.price.domain.model.Price;
import com.inditex.price.application.utils.UseCase;
import com.inditex.price.domain.exceptions.NotFoundException;
import com.inditex.price.domain.exceptions.model.ApplicationErrorType;
import com.inditex.price.application.ports.driving.FindByProductQuery;
import com.inditex.price.application.ports.driving.FindByProductResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import static com.inditex.price.application.mappers.ApplicationMapper.MAPPER;

@UseCase
@RequiredArgsConstructor
public class FilterPriceUseCase implements ForFilteringPricesPort {

  private final PriceRepositoryPort dataRepository;

  @Override
  @SneakyThrows
  public FindByProductResult findByProduct(FindByProductQuery findByProductQuery) {
      Price price = dataRepository.findByProduct(findByProductQuery.productId(),
              findByProductQuery.brandId(), findByProductQuery.applicationDate());
      if (Objects.isNull(price)) {
      throw new NotFoundException(ApplicationErrorType.RESOURCE_NOT_FOUND.toError());
    } else {
      return MAPPER.toFindByProductResult(price);
    }
  }

}
