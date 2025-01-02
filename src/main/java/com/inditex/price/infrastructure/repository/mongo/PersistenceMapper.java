package com.inditex.price.infrastructure.repository.mongo;

import com.inditex.price.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersistenceMapper {

  PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

  Price toPrice(PriceEntity val);

}
