package com.inditex.price.application.validation;

import com.inditex.price.application.ports.driving.FindByProductResult;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindByProductResultTest {

  @Test
  void validationOk() {
    new FindByProductResult
        (35455L,
            1L,
            LocalDateTime.of(2022, 1, 1, 12, 0, 0),
            LocalDateTime.of(2022, 12, 31, 12, 0, 0),
            35.50
        );
  }

  @Test
  void productIdValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (null,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  void brandIdValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              null,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  void startDateValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              null,
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              35.50
          );
    });
  }

  @Test
  void endDateValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              null,
              35.50
          );
    });
  }

  @Test
  void priceValidationFails() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      new FindByProductResult
          (1L,
              1L,
              LocalDateTime.of(2022, 1, 1, 12, 0, 0),
              LocalDateTime.of(2022, 12, 31, 12, 0, 0),
              null
          );
    });
  }
}
