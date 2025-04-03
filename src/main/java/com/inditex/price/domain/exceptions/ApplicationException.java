package com.inditex.price.domain.exceptions;

import com.inditex.price.domain.exceptions.model.ApplicationError;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final List<ApplicationError> errors;

  public ApplicationException(ApplicationError e) {
    super(e.getMessage());
    this.errors = new ArrayList<>();
    this.errors.add(e);
  }

}
