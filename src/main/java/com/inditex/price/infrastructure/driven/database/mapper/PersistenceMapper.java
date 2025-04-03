package com.inditex.price.infrastructure.driven.database.mapper;

import com.inditex.price.domain.model.Price;
import com.inditex.price.infrastructure.driven.database.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersistenceMapper {

  PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

  Price toPrice(PriceEntity val);

}
