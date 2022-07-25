package com.smart.equip.randomnumbersum.service;

import com.smart.equip.randomnumbersum.constants.Constant;
import com.smart.equip.randomnumbersum.model.Response;
import com.smart.equip.randomnumbersum.util.Utility;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Author: Nancy Chauhan
 * This is service 1 which generates 2 to 4 random numbers.
 */
@Service
public class RandomNumCreateServiceImpl implements RandomNumCreateService{

    @Override
    public Response getRandomNumbers() {

        Response response = new Response();
        Random randObj = new Random();

        //Calculating, how many numbers to be added? The Min count should be 2 and Max 4
        int limit = randObj.nextInt(Constant.MAX_NUMBERS_FOR_SUM) + Constant.MIN_NUMBERS_FOR_SUM;

        //Saving the randoms into list within ranges 0(inclusive) to 10(exclusive)
        List<Integer> randomNumbers = randObj.ints(Constant.MIN_BOUND,Constant.MAX_BOUND).limit(limit).boxed().collect(Collectors.toList());

        response.setRandomNumbers(randomNumbers);
        response.setMessage(Utility.prepareMessage(randomNumbers));
        return response;
    }
}
