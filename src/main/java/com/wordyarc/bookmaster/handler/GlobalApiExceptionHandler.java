package com.wordyarc.bookmaster.handler;

import com.wordyarc.bookmaster.dto.exception.*;
import com.wordyarc.bookmaster.exception.*;
import jakarta.validation.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class GlobalApiExceptionHandler {

    public static final String SERVER_ERROR = "Произошла ошибка на сервере";

    public static final String INVALID_FIELDS = "Некоторые поля не прошли валидацию";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDto> handleApiException(ApiException exception) {
        if (exception.getHttpStatus().is5xxServerError()) {
            log.error("ApiException {}: {}", exception.getHttpStatus(), exception.getMessage());

            return new ResponseEntity<>(new ApiErrorDto(SERVER_ERROR), exception.getHttpStatus());
        }

        log.debug("ApiException {}: {}", exception.getHttpStatus(), exception.getMessage());

        return new ResponseEntity<>(new ApiErrorDto(exception.getMessage()), exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentsNotValid(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors().stream()
            .map(fieldError -> new InvalidFieldDto(fieldError.getField(), fieldError.getDefaultMessage()))
            .toList();

        return ResponseEntity.badRequest().body(new InvalidFieldsDto(INVALID_FIELDS, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        var errors = exception.getConstraintViolations().stream()
            .map(cv -> {
                var fileName = cv.getPropertyPath().toString();
                for (var propertyPath : cv.getPropertyPath()) {
                    fileName = propertyPath.getName();
                }
                return new InvalidFieldDto(fileName, cv.getMessage());
            }).toList();

        return ResponseEntity.badRequest().body(new InvalidFieldsDto(INVALID_FIELDS, errors));
    }
}
