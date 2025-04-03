package com.inditex.price.infrastructure.driving.rest.mapper;

import com.inditex.price.application.ports.driving.FindByProductQuery;
import com.inditex.price.application.ports.driving.FindByProductResult;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.inditex.price.infrastructure.driving.rest.*;
import com.inditex.price.infrastructure.driving.rest.utils.MessageUtil;
import com.inditex.price.domain.exceptions.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.inditex.price.domain.exceptions.model.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import static com.inditex.price.infrastructure.driving.rest.exceptions.GlobalExceptionHandler.HTTP_400_MISSING_PARAM;

@Mapper
public interface WebMapper {

  WebMapper MAPPER = Mappers.getMapper(WebMapper.class);

  FindByProductQuery toFindByProductQuery(FindByProductRequestDTO val);

  default FindByProductResponseDTO toFindByProductResponse(FindByProductResult val) {
    FindByProductResponseDTO responseDTO = new FindByProductResponseDTO();
    responseDTO.setPrice(toPriceDTO(val));
    responseDTO.setResponseStatus(FindByProductResponseDTO.ResponseStatusEnum.SUCCESS);
    responseDTO.setErrors(null);
    return responseDTO;
  }

  @Mapping(target = "responseStatus", expression = "java(BadRequestResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDto(ex,status))")
  BadRequestResponseDTO toBadRequestDto(ServletRequestBindingException ex, HttpStatusCode status);
  default List<ErrorDTO> toErrorDto(ServletRequestBindingException ex, HttpStatusCode status) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setCode(status.toString());
    errorDTO.setMessage(ex.getMessage());
    return Collections.singletonList(errorDTO);
  }

  @Mapping(target = "responseStatus", expression = "java(BadRequestResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDto(ex,status))")
  BadRequestResponseDTO toBadRequestDto(MissingServletRequestParameterException ex, HttpStatusCode status);
  default List<ErrorDTO> toErrorDto(MissingServletRequestParameterException ex, HttpStatusCode status) {
    String message = MessageUtil.getFormattedMsg(HTTP_400_MISSING_PARAM, ex.getParameterName(), ex.getParameterType());
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setCode(status.toString());
    errorDTO.setMessage(message);
    return Collections.singletonList(errorDTO);
  }

  @Mapping(target = "responseStatus", expression = "java(BadRequestResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDto(ex,status))")
  BadRequestResponseDTO toBadRequestDto(MethodArgumentNotValidException ex, HttpStatusCode status);
  default List<ErrorDTO> toErrorDto(MethodArgumentNotValidException ex, HttpStatusCode status) {
      return ex.getBindingResult().getFieldErrors().stream().map(
            fieldError -> {
              ErrorDTO errorDTO = new ErrorDTO();
              errorDTO.setCode(status.toString());
              errorDTO.setMessage(fieldError.getObjectName() + "." + fieldError.getField() + " "
                      + fieldError.getDefaultMessage());
              return errorDTO;}).toList();
  }

  @Mapping(target = "responseStatus", expression = "java(InternalServerErrorResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDTOFromApplicationError(ex.getErrors()))")
  InternalServerErrorResponseDTO toInternalServerErrorDto(ApplicationException ex);

  @Mapping(target = "responseStatus", expression = "java(NotFoundResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDTOFromApplicationError(ex.getErrors()))")
  NotFoundResponseDTO toNotFoundDto(ApplicationException ex);

  @Mapping(target = "responseStatus", expression = "java(InternalServerErrorResponseDTO.ResponseStatusEnum.ERROR)")
  @Mapping(target = "errors", expression = "java(toErrorDto(ex))")
  InternalServerErrorResponseDTO toInternalServerErrorDto(RuntimeException ex);
  default List<ErrorDTO> toErrorDto(RuntimeException ex) {
      String developerMsg = ex.getMessage();
      if (Objects.nonNull(ex.getCause()) && Objects.nonNull(ex.getCause().getCause())) {
        developerMsg = ex.getCause().getCause().getMessage();
      }
      ErrorDTO errorDTO = new ErrorDTO();
      errorDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
      errorDTO.setMessage(developerMsg);
      return Collections.singletonList(errorDTO);
  }

  PriceDTO toPriceDTO(FindByProductResult val);

  ErrorDTO toErrorDTOFromApplicationError(ApplicationError val);

  List<ErrorDTO> toErrorDTOFromApplicationError(List<ApplicationError> val);

}
