package com.inditex.price.application.rest.exceptions;

import static com.inditex.price.application.rest.mapper.WebMapper.MAPPER;

import com.inditex.price.application.rest.dtos.BadRequestResponseDTO;
import com.inditex.price.application.rest.dtos.InternalServerErrorResponseDTO;
import com.inditex.price.application.rest.dtos.NotFoundResponseDTO;
import com.inditex.price.application.utils.MessageUtil;
import com.inditex.price.domain.exceptions.ApplicationException;
import com.inditex.price.domain.exceptions.NotFoundException;
import com.inditex.price.domain.exceptions.model.ErrorType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
import com.inditex.price.domain.exceptions.model.Error;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageUtil messageUtil;
  public static final String HTTP_400_MISSING_PARAM = "Required Param {0} of type {1} is missing";

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    log.error("handleMissingServletRequestParameter - exception", ex);
    Error error = Error.builder().message(
        messageUtil.getFormattedMsg(HTTP_400_MISSING_PARAM, ex.getParameterName(),
            ex.getParameterType())).code(status.toString()).build();
    return handleExceptionInternal(ex, error, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    log.error("handleServletRequestBindingException - exception", ex);
    BadRequestResponseDTO responseDTO = new BadRequestResponseDTO();
    responseDTO.setErrors(
        MAPPER.toErrorDTO(Collections.singletonList(ErrorType.BAD_REQUEST.toError())));
    responseDTO.setResponseStatus(BadRequestResponseDTO.ResponseStatusEnum.ERROR);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseDTO);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.error("handleMethodArgumentNotValid - exception: ", ex);
    List<Error> errorList = ex.getBindingResult().getFieldErrors().stream().map(
            fieldError -> Error.builder().message(
                fieldError.getObjectName() + "." + fieldError.getField() + " "
                    + fieldError.getDefaultMessage()).code(status.toString()).build())
        .collect(Collectors.toList());
    BadRequestResponseDTO responseDTO = new BadRequestResponseDTO();
    responseDTO.setErrors(MAPPER.toErrorDTO(errorList));
    responseDTO.setResponseStatus(BadRequestResponseDTO.ResponseStatusEnum.ERROR);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseDTO);
  }

  @ExceptionHandler({ApplicationException.class})
  public ResponseEntity<Object> handleBaseException(ApplicationException ex, WebRequest request) {
    log.error("handleBaseException - exception", ex);
    return getObjectResponseEntity(ex);
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
    NotFoundResponseDTO responseDTO = new NotFoundResponseDTO();
    responseDTO.setErrors(
        MAPPER.toErrorDTO(Collections.singletonList(ErrorType.RESOURCE_NOT_FOUND.toError())));
    responseDTO.setResponseStatus(NotFoundResponseDTO.ResponseStatusEnum.ERROR);
    return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(responseDTO);
  }

  private ResponseEntity<Object> getObjectResponseEntity(RuntimeException ex) {
    String developerMsg = ex.getMessage();
    if (Objects.nonNull(ex.getCause()) && Objects.nonNull(ex.getCause().getCause())) {
      developerMsg = ex.getCause().getCause().getMessage();
    }
    Error errorDto = Error.builder().message(developerMsg)
        .code(HttpStatus.INTERNAL_SERVER_ERROR.toString()).build();
    InternalServerErrorResponseDTO responseDTO = new InternalServerErrorResponseDTO();
    responseDTO.setErrors(MAPPER.toErrorDTO(Collections.singletonList(errorDto)));
    responseDTO.setResponseStatus(InternalServerErrorResponseDTO.ResponseStatusEnum.ERROR);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(responseDTO);
  }

  private void printErrors(ApplicationException ex) {
    Optional.ofNullable(ex.getErrors())
        .ifPresent(errors -> errors.forEach(error -> log.error(error.toString())));
  }

}
