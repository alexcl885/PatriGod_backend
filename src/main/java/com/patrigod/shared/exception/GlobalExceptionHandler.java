package com.patrigod.shared.exception;

import com.patrigod.shared.exception.type.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.patrigod.shared.exception.model.CustomError;
import com.patrigod.shared.exception.type.EntityNotFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles exceptions of type EntityNotFound.
     *
     * @param ex the thrown exception
     * @return a response with the error message and HTTP status code 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity< CustomError> handleEntityNotFound(EntityNotFoundException ex) {
        CustomError error =  new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
    }

    /**
     * Exception to conflict exception
     * */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity< CustomError> handleEntityNotFound(ConflictException ex) {
        CustomError error =  new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception to validation independency
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        CustomError error = new CustomError(errors, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
