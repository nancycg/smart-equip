package com.smart.equip.randomnumbersum.service;

import com.smart.equip.randomnumbersum.model.Request;
import com.smart.equip.randomnumbersum.model.Response;
import com.smart.equip.randomnumbersum.util.Utility;
import org.springframework.stereotype.Service;

/**
 * This is service 2 which is verifying the sum received in request with the actual sum.
 */
@Service
public class RandomNumSumCheckerServiceImpl implements RandomNumSumCheckerService{

    @Override
    public Response verifySum(Request request) {
        Response response = new Response();
        int actualSum = request.getRandomNumbers().stream().reduce(0,Integer::sum);

        response.setSuccess(actualSum == request.getSum()? true: false);

        response.setRandomNumbers(request.getRandomNumbers());
        response.setMessage(Utility.prepareMessage(response.isSuccess()));
        return response;
    }
}
