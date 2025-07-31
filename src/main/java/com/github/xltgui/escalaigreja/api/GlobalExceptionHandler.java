package com.github.xltgui.escalaigreja.api;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<MyFieldError> myFieldErrorList = fieldErrors.stream()
                .map(fe -> new MyFieldError(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                myFieldErrorList
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = "Malformed JSON request. Please check the data types, especially for 'role'.";
        String fieldError = "";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            if (cause.getTargetType().isEnum()) {
/*
                System.out.println("CAUSE PATH=" + cause.getPath());
                System.out.println("GET CAUSE PATH -1=" + cause.getPath().get(cause.getPath().size() - 1));
                System.out.println("GE FIELD NAME=" + cause.getPath().get(cause.getPath().size() - 1).getFieldName());
*/
                fieldError = cause.getPath().get(cause.getPath().size() - 1).getFieldName();

                errorMessage = String.format("Invalid values. Accepted values are: %s.",
                        Arrays.toString(cause.getTargetType().getEnumConstants()));
            }
        }

        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                List.of(new MyFieldError(fieldError, errorMessage))
        );
    }
}
