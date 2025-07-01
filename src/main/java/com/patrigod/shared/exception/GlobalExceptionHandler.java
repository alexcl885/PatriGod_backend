package com.patrigod.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.patrigod.shared.exception.model.CustomError;
import com.patrigod.shared.exception.type.EntityNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja excepciones de tipo EntityNotFound.
     * 
     * @param ex la excepción lanzada
     * @return una respuesta con el mensaje de error y el código de estado 404
     */
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity< CustomError> handleEntityNotFound(EntityNotFound ex) {
        CustomError error =  new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
    }
    
}
