package com.inditex.price.domain.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {

  BAD_REQUEST("400", "Bad Request. Request is invalid or required data missing."),
  RESOURCE_NOT_FOUND("404", "Resource not found."),
  INTERNAL_SERVER_ERROR("500", "Internal server error, failed to process the request."),
  INVALID_DATA_FOUND("400", "Invalid data found for {0}");

  String code;
  String message;

  public Error toError() {
    return Error.builder().code(this.getCode()).message(this.getMessage()).build();
  }

}
