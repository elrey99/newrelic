package com.porvenir.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID =1L; // Change for autogenerate

    private final String code;

    private final HttpStatus httpStatus;

    private final String message;
}
