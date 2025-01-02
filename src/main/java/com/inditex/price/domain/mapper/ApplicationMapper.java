package com.inditex.price.domain.mapper;

import com.inditex.price.domain.Price;
import com.inditex.price.domain.services.FindByProductResult;
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
