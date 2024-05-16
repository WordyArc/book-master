package com.wordyarc.bookmaster.exception;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;
import org.springframework.http.*;

@Getter
public class ApiException extends RuntimeException {

    @Schema(description = "Код ошибки", example = "404")
    private final HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

}
