package com.inditex.price.infrastructure.driving.api.exceptions;

import static com.inditex.price.infrastructure.driving.api.mappers.WebMapper.MAPPER;
import com.inditex.price.domain.exceptions.ApplicationException;
import com.inditex.price.domain.exceptions.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String HTTP_400_MISSING_PARAM = "Required Param {0} of type {1} is missing";

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    log.error("handleMissingServletRequestParameter - exception", ex);
    return handleExceptionInternal(ex, MAPPER.toBadRequestDto(ex, status), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    log.error("handleServletRequestBindingException - exception", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(MAPPER.toBadRequestDto(ex, status));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.error("handleMethodArgumentNotValid - exception: ", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(MAPPER.toBadRequestDto(ex, status));
  }

  @ExceptionHandler({ApplicationException.class})
  public ResponseEntity<Object> handleBaseException(ApplicationException ex, WebRequest request) {
    printErrors(ex);
    log.error("handleBaseException - exception", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(MAPPER.toInternalServerErrorDto(ex));
  }

  @ExceptionHandler({RuntimeException.class, IllegalArgumentException.class})
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    log.error("handleRuntimeException - exception", ex);
    return getObjectResponseEntity(ex);
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Object> handleInvalidRequestException(NotFoundException ex,
      WebRequest request) {
    printErrors(ex);
    log.error("handleInvalidRequestException: ", ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(MAPPER.toNotFoundDto(ex));
  }

  private ResponseEntity<Object> getObjectResponseEntity(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(MAPPER.toInternalServerErrorDto(ex));
  }

  private void printErrors(ApplicationException ex) {
    Optional.ofNullable(ex.getErrors())
        .ifPresent(errors -> errors.forEach(error -> log.error(error.toString())));
  }

}
