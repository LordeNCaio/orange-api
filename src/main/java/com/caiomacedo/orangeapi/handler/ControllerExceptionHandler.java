package com.caiomacedo.orangeapi.handler;

import com.caiomacedo.orangeapi.exception.PersonAlreadyExistsException;
import com.caiomacedo.orangeapi.exception.PersonNotFoundException;
import com.caiomacedo.orangeapi.exception.VaccineNameInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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

    @ExceptionHandler(value = VaccineNameInvalidException.class)
    public ErrorMessage vaccineNameInvalidException() {
        return new ErrorMessage(
                LocalDateTime.now(),
                "Vaccine name is invalid");
    }

}