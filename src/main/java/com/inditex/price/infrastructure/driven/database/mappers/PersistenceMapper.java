package com.inditex.price.infrastructure.driven.database.mappers;

import com.inditex.price.domain.model.Price;
import com.inditex.price.infrastructure.driven.database.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersistenceMapper {

  PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

  Price toPrice(PriceEntity val);

}
