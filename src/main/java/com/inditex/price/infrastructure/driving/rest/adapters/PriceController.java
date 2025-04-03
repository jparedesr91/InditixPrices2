package com.inditex.price.infrastructure.driving.rest.adapters;

import static com.inditex.price.infrastructure.driving.rest.mapper.WebMapper.MAPPER;
import com.inditex.price.application.ports.driving.ForFilteringPricesPort;
import com.inditex.price.infrastructure.driving.rest.FindByProductRequestDTO;
import com.inditex.price.infrastructure.driving.rest.FindByProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements FilterApi {

  private final ForFilteringPricesPort filteringPrices;

  @Override
  public ResponseEntity<FindByProductResponseDTO> findByProduct(
      FindByProductRequestDTO findByProductRequestDTO) {
    return ResponseEntity
        .ok(MAPPER.toFindByProductResponse(
                filteringPrices.findByProduct(
                MAPPER.toFindByProductQuery(findByProductRequestDTO))));
  }

}