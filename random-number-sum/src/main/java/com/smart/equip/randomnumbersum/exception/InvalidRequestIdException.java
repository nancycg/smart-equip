package com.smart.equip.randomnumbersum.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidRequestIdException extends RuntimeException{
    private String msg;
}
