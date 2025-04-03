package com.inditex.price.domain.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApplicationErrorType {

  VALIDATION_ERROR("E01", "Validation Error. Invalid or required data missing."),
  RESOURCE_NOT_FOUND("E02", "Resource not found."),
  SERVER_ERROR("E03", "Internal server error, failed to process the request.");

  String code;
  String message;

  public ApplicationError toError() {
    return ApplicationError.builder().code(this.getCode()).message(this.getMessage()).build();
  }

}
