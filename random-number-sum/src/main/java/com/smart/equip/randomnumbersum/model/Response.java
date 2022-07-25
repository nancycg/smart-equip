package com.smart.equip.randomnumbersum.model;

import lombok.Data;
import java.util.List;

@Data
public class Response {
    private String id;
    private String message;
    private List<Integer> randomNumbers;
    private boolean isSuccess;
}
