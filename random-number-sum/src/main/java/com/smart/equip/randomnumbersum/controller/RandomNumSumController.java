package com.smart.equip.randomnumbersum.controller;

import com.smart.equip.randomnumbersum.model.Request;
import com.smart.equip.randomnumbersum.model.Response;
import com.smart.equip.randomnumbersum.service.RandomNumCreateService;
import com.smart.equip.randomnumbersum.service.RandomNumSumCheckerService;
import com.smart.equip.randomnumbersum.util.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Nancy Chauhan
 * Date: 24th July 2022
 *
 * 1) First End-point["/query"]: Receives request for random numbers,
 * this will return 2 or more random numbers to client.
 *
 * 1) Second End-point["/validate"]: Receives request to validate the sum of random numbers,
 * After validating the actual sum with the sum received in request it will return HTTP status
 */

@RestController
@RequestMapping(path = "/smart-equip")
public class RandomNumSumController {

    private final RandomNumSumCheckerService randomNumSumCheckerService;
    private final RandomNumCreateService randomNumCreateService;

    // Storing requestId with random numbers sent for this requestId
    private static Map<String, List<Integer>> data = new HashMap<>();

    public RandomNumSumController(RandomNumSumCheckerService randomNumSumCheckerService, RandomNumCreateService randomNumCreateService) {
        this.randomNumSumCheckerService = randomNumSumCheckerService;
        this.randomNumCreateService = randomNumCreateService;
    }


    @GetMapping(value = "/query")
    public ResponseEntity<Response>  getRandomNumbers(){
        Response prepareResponse = randomNumCreateService.getRandomNumbers();
        final String requestId = UUID.randomUUID().toString();
        prepareResponse.setId(requestId);
        data.put(requestId,prepareResponse.getRandomNumbers());
        return new ResponseEntity<>(prepareResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/verify" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> verifySum(@Valid @RequestBody final Request request) {

        Response response = null;
        if(request.getId() != null && Utility.validateRandomNumbersInRequest(request, data)){
            response = randomNumSumCheckerService.verifySum(request);
        }

        return response != null && response.isSuccess() ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
