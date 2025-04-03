package com.inditex.price.application.mappers;

import com.inditex.price.domain.model.Price;
import com.inditex.price.application.ports.driving.FindByProductResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationMapper {

  ApplicationMapper MAPPER = Mappers.getMapper(ApplicationMapper.class);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "brandId", source = "brand.id")
  FindByProductResult toFindByProductResult(Price val);

}
