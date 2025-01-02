package com.inditex.price.application.rest;


import static com.inditex.price.application.rest.mapper.WebMapper.MAPPER;

import com.inditex.price.application.rest.api.FilterApi;
import com.inditex.price.application.rest.dtos.FindByProductRequestDTO;
import com.inditex.price.application.rest.dtos.FindByProductResponseDTO;
import com.inditex.price.application.utils.WebAdapter;
import com.inditex.price.domain.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class PriceController implements FilterApi {

  private final PriceService priceService;

  @Override
  public ResponseEntity<FindByProductResponseDTO> findByProduct(
      FindByProductRequestDTO findByProductRequestDTO) {
    return ResponseEntity
        .ok(MAPPER.toFindByProductResponse(
            priceService.findByProduct(
                MAPPER.toFindByProductQuery(findByProductRequestDTO))));
  }

}