package com.inditex.price.domain.exceptions;

import com.inditex.price.domain.exceptions.model.Error;

public class NotFoundException extends ApplicationException {

  public NotFoundException(Error e) {
    super(e);
  }

}
