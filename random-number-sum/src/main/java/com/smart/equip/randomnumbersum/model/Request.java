package com.smart.equip.randomnumbersum.model;

import lombok.Data;
import java.util.List;

@Data
public class Request {
    private String id;
    private int sum;
    private String message;
    private List<Integer> randomNumbers;
}
