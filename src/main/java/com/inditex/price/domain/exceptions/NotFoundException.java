package com.inditex.price.domain.exceptions;

import com.inditex.price.domain.exceptions.model.ApplicationError;

public class NotFoundException extends ApplicationException {

  public NotFoundException(ApplicationError e) {
    super(e);
  }

}
