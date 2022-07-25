package com.smart.equip.randomnumbersum.service;

import com.smart.equip.randomnumbersum.model.Request;
import com.smart.equip.randomnumbersum.model.Response;

public interface  RandomNumSumCheckerService {
    Response verifySum(Request request);
}
