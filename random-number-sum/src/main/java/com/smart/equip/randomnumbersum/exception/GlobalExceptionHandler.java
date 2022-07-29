package com.smart.equip.randomnumbersum.exception;

import com.smart.equip.randomnumbersum.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRandomNumbersException.class)
    public ResponseEntity<Object> invalidRandomNumbersHandler(InvalidRandomNumbersException ex) {
        Response response = new Response();
        response.setMessage(ex.getMsg());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestIdException.class)
    public ResponseEntity<Object> invalidRequestIdException(InvalidRequestIdException ex) {
        Response response = new Response();
        response.setMessage(ex.getMsg());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> jsonValidation(HttpMessageNotReadableException ex) {
        Response response = new Response();
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> invalidRequestException(InvalidRequestException ex) {
        Response response = new Response();
        response.setMessage(ex.getMsg());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
