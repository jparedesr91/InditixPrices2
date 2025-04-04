package com.inditex.price.infrastructure.driving.api.adapters;

import static com.inditex.price.infrastructure.driving.api.mappers.WebMapper.MAPPER;

import com.inditex.price.application.ports.driving.FindByProductQuery;
import com.inditex.price.application.ports.driving.FindByProductResult;
import com.inditex.price.application.ports.driving.ForFilteringPricesPort;
import com.inditex.price.infrastructure.driving.api.FindByProductRequestDTO;
import com.inditex.price.infrastructure.driving.api.FindByProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements FilterApi {

  private final ForFilteringPricesPort filteringPrices;

  @Override
  public ResponseEntity<FindByProductResponseDTO> findByProduct(FindByProductRequestDTO findByProductRequestDTO) {
    FindByProductQuery findByProductQuery = MAPPER.toFindByProductQuery(findByProductRequestDTO);
    FindByProductResult findByProductResult = filteringPrices.findByProduct(findByProductQuery);
    FindByProductResponseDTO findByProductResponseDTO = MAPPER.toFindByProductResponse(findByProductResult);
    return ResponseEntity.ok(findByProductResponseDTO);
  }

}