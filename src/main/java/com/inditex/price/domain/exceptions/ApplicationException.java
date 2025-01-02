package com.inditex.price.domain.exceptions;

import com.inditex.price.domain.exceptions.model.Error;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final Collection<Error> errors;

  public ApplicationException(Error e) {
    super(e.getMessage());
    this.errors = new ArrayList<>();
    this.errors.add(e);
  }

}
