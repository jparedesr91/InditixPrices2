package com.inditex.price.infrastructure.adapters.driving.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import com.inditex.price.application.ports.driving.FindByProductQuery;
import com.inditex.price.application.ports.driving.FindByProductResult;
import java.time.LocalDateTime;
import com.inditex.price.domain.exceptions.model.ApplicationError;
import com.inditex.price.infrastructure.driving.rest.ErrorDTO;
import com.inditex.price.infrastructure.driving.rest.FindByProductRequestDTO;
import com.inditex.price.infrastructure.driving.rest.FindByProductResponseDTO;
import com.inditex.price.infrastructure.driving.rest.PriceDTO;
import com.inditex.price.infrastructure.driving.rest.mapper.WebMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class WebMapperTest {

    private final WebMapper mapper = Mappers.getMapper(WebMapper.class);

    @Test
    void testMappingFromFindByProductRequestDTOtoFindByProductQuery() {
        FindByProductQuery expected = getFindByProductQuery();
        FindByProductQuery result = mapper.toFindByProductQuery(getFindByProductRequestDTO());
        assertNotNull("FindByProductQuery should not be null", result);
        assertEquals(expected.productId(), result.productId());
        assertEquals(expected.brandId(), result.brandId());
        assertEquals(expected.applicationDate(), result.applicationDate());
    }

    @Test
    void testMappingFromFindByProductResultToFindByProductResponseDTO() {
        FindByProductResponseDTO expected = getFindByProductResponseDTO();
        FindByProductResponseDTO result = mapper.toFindByProductResponse(getFindByProductResult());
        assertNotNull("FindByProductResult should not be null", result);
        assertEquals(expected.getResponseStatus(), result.getResponseStatus());
        assertEquals(expected.getErrors(), result.getErrors());
        assertEquals(expected.getPrice(), result.getPrice());
    }

    @Test
    void testMappingFromFindByProductResultToPriceDTO() {
        PriceDTO expected = getPriceDTO();
        PriceDTO result = mapper.toPriceDTO(getFindByProductResult());
        assertNotNull("PriceDTO should not be null", result);
        assertEquals(expected.getProductId(), result.getProductId());
        assertEquals(expected.getBrandId(), result.getBrandId());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
        assertEquals(expected.getPrice(), result.getPrice());
    }

    @Test
    void testMappingFromErrorToErrorDTO() {
        ErrorDTO expected = getErrorDTO();
        ErrorDTO result = mapper.toErrorDTOFromApplicationError(getError());
        assertNotNull("ErrorDTO should not be null", result);
        assertEquals(expected.getCode(), result.getCode());
        assertEquals(expected.getMessage(), result.getMessage());
    }

    private FindByProductResult getFindByProductResult() {
        return new FindByProductResult
                (35455L,
                        1L,
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        LocalDateTime.of(2020, 6, 14, 17, 0, 0),
                        35.50
                );
    }

    private FindByProductQuery getFindByProductQuery() {
        return new FindByProductQuery(
                1L,
                1L,
                LocalDateTime.of(2020, 6, 14, 17, 0, 0)
        );
    }

    private FindByProductRequestDTO getFindByProductRequestDTO() {
        return new FindByProductRequestDTO(
                1L,
                1L,
                "2020-06-14T17:00:00");
    }

    private FindByProductResponseDTO getFindByProductResponseDTO() {
        FindByProductResponseDTO findByProductResponseDTO = new FindByProductResponseDTO();
        findByProductResponseDTO.setPrice(getPriceDTO());
        findByProductResponseDTO.setResponseStatus(FindByProductResponseDTO.ResponseStatusEnum.SUCCESS);
        findByProductResponseDTO.setErrors(null);
        return findByProductResponseDTO;
    }

    private PriceDTO getPriceDTO() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProductId(35455L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate("2020-06-14T17:00:00");
        priceDTO.setEndDate("2020-06-14T17:00:00");
        priceDTO.setPrice(35.50);
        return priceDTO;
    }

    private ErrorDTO getErrorDTO() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("400");
        errorDTO.setMessage("Bad request");
        return errorDTO;
    }

    private ApplicationError getError() {
        ApplicationError error = new ApplicationError();
        error.setCode("400");
        error.setMessage("Bad request");
        return error;
    }
}
