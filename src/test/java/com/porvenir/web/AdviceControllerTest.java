package com.porvenir.web;

import com.porvenir.domain.dto.ResponseDto;
import com.porvenir.exception.ApiException;
import com.porvenir.util.RequestResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AdviceControllerTest {

    @InjectMocks
    AdviceController adviceController;

    @Mock
    RequestResponseUtils requestResponseUtils;

    @Test
    void apiExceptionHandler() {
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("value", String.class, "parameter", null, null);

        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result = adviceController.apiExceptionHandler(exception);
        Assertions.assertNotNull(result);
    }

    @Test
    void apiMissingException() {
        MissingServletRequestParameterException mx = new MissingServletRequestParameterException("","");

        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result = adviceController.apiMissingException(mx);
        Assertions.assertNotNull(result);
    }

    @Test
    void exception() {
        Exception exception = new Exception();

        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result = adviceController.Exception(exception);
        Assertions.assertNotNull(result);
    }

    @Test
    void apiSQLException() {
        SQLException exception = new SQLException("Error executing SQL query");

        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result = adviceController.apiSQLException(exception);
        Assertions.assertNotNull(result);
    }

    @Test
    void illegalStateException() {
        IllegalStateException exception = new IllegalStateException();

        when(requestResponseUtils.construirRespuesta(anyString(), anyString(), any())).thenReturn(new ResponseDto());

        ResponseEntity<ResponseDto> result = adviceController.illegalStateException(exception);
        Assertions.assertNotNull(result);
    }

    @Test
    void handleValidationExceptions_ShouldReturnBadRequest() {

        MethodArgumentNotValidException mockException = mock(MethodArgumentNotValidException.class);

        ResponseEntity<ResponseDto> responseEntity = adviceController.handleValidationExceptions(mockException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testHandlerApiException() {
        ResponseEntity<ResponseDto> result = adviceController.handlerApiException(new ApiException("01", HttpStatus.OK, "ok"));
        Assertions.assertNotNull(result);
    }
}
