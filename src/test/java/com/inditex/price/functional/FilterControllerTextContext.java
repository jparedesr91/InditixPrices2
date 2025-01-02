package com.inditex.price.functional;

import com.inditex.price.application.rest.dtos.FindByProductResponseDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class FilterControllerTextContext {

  private ResponseEntity<FindByProductResponseDTO> response;

}
