package com.smart.equip.randomnumbersum.exception;

import com.smart.equip.randomnumbersum.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRandomNumbersException.class)
    public ResponseEntity<Object> InvalidRandomNumbersHandler(InvalidRandomNumbersException ex) {
        Response response = new Response();
        response.setMessage(ex.getMsg());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestIdException.class)
    public ResponseEntity<Object> InvalidRequestIdException(InvalidRequestIdException ex) {
        Response response = new Response();
        response.setMessage(ex.getMsg());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
