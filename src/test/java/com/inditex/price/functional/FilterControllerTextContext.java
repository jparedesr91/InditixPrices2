package com.inditex.price.functional;

import com.inditex.price.infrastructure.driving.api.FindByProductResponseDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class FilterControllerTextContext {

  private ResponseEntity<FindByProductResponseDTO> response;

}
