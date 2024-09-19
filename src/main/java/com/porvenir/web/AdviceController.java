package com.porvenir.web;

import com.porvenir.domain.dto.ResponseDto;
import com.porvenir.exception.ApiException;
import com.porvenir.util.RequestResponseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

import static com.porvenir.util.Constant.*;

@RestControllerAdvice
@Log4j2
public class AdviceController {

    @Autowired
    private RequestResponseUtils requestResponseUtils;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto> apiExceptionHandler(MethodArgumentTypeMismatchException ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_400, MESSAGE_400, null);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseDto> apiMissingException(MissingServletRequestParameterException ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_400, MESSAGE_400, null);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> Exception(Exception ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_206, MESSAGE_206, null);

        return new ResponseEntity<>(responseDto, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDto> apiSQLException(SQLException ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_500, MESSAGE_500, null);

        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseDto> illegalStateException(IllegalStateException ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_206, MESSAGE_206, null);

        return new ResponseEntity<>(responseDto, HttpStatus.PARTIAL_CONTENT);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_400, RESPONSE_REQUEST_ERROR, null);

        return new ResponseEntity<> (responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ResponseDto> handlerApiException (ApiException apiException)  {

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(apiException.getCode(),
            apiException.getMessage(), null );

        return new ResponseEntity<> (responseDto, apiException.getHttpStatus());
    }
}
