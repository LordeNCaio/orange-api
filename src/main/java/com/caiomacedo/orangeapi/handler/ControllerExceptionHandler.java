package com.caiomacedo.orangeapi.handler;

import com.caiomacedo.orangeapi.exception.PersonAlreadyExistsException;
import com.caiomacedo.orangeapi.exception.PersonNotFoundException;
import com.caiomacedo.orangeapi.exception.VaccineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = PersonAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage personAlreadyExistsException() {
        return new ErrorMessage(
                LocalDateTime.now(),
                "Current person already exits");
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage personNotFoundException() {
        return new ErrorMessage(
                LocalDateTime.now(),
                "Person not exists");
    }

    @ExceptionHandler(value = VaccineNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage vaccineNotFoundException() {
        return new ErrorMessage(
                LocalDateTime.now(),
                "Vaccine not exists");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}