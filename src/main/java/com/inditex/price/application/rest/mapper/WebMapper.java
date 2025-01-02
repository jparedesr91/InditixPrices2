package com.inditex.price.application.rest.mapper;

import com.inditex.price.application.rest.dtos.ErrorDTO;
import com.inditex.price.application.rest.dtos.FindByProductRequestDTO;
import com.inditex.price.application.rest.dtos.FindByProductResponseDTO;
import com.inditex.price.application.rest.dtos.FindByProductResponseDTO.ResponseStatusEnum;
import com.inditex.price.application.rest.dtos.PriceDTO;
import com.inditex.price.domain.services.FindByProductQuery;
import com.inditex.price.domain.services.FindByProductResult;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.inditex.price.domain.exceptions.model.Error;

@Mapper
public interface WebMapper {

  WebMapper MAPPER = Mappers.getMapper(WebMapper.class);

  FindByProductQuery toFindByProductQuery(FindByProductRequestDTO val);

  default FindByProductResponseDTO toFindByProductResponse(FindByProductResult val) {
    FindByProductResponseDTO responseDTO = new FindByProductResponseDTO();
    responseDTO.setPrice(toPriceDTO(val));
    responseDTO.setResponseStatus(ResponseStatusEnum.SUCCESS);
    responseDTO.setErrors(null);
    return responseDTO;
  }

  PriceDTO toPriceDTO(FindByProductResult val);

  ErrorDTO toErrorDTO(Error val);

  List<ErrorDTO> toErrorDTO(List<Error> val);

}
