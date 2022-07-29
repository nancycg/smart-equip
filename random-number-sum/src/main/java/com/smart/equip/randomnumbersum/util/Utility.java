package com.smart.equip.randomnumbersum.util;

import com.smart.equip.randomnumbersum.constants.Constant;
import com.smart.equip.randomnumbersum.exception.InvalidRandomNumbersException;
import com.smart.equip.randomnumbersum.exception.InvalidRequestException;
import com.smart.equip.randomnumbersum.exception.InvalidRequestIdException;
import com.smart.equip.randomnumbersum.model.Request;

import java.util.List;
import java.util.Map;

public class Utility {

    public static String prepareMessage(boolean isSuccess) {
        String msg;

        if(isSuccess){
            msg = "That's great";
        } else {
            msg = "Incorrect Sum. Please try again";
        }
        return msg;
    }

    public static String prepareMessage(List<Integer> randoms) {
        return "Here you go, solve the question: Please sum the numbers " + randoms.toString();
    }

    public static boolean validateRandomNumbersInRequest(Request req, Map<String,List<Integer>> validData) {

        if (!validData.containsKey(req.getId()))
            throw new InvalidRequestIdException("Invalid requestId. Please try again");

        if (!req.getRandomNumbers().containsAll(validData.get(req.getId())) || (req.getRandomNumbers().size() != validData.get(req.getId()).size()))
            throw new InvalidRandomNumbersException("Invalid random numbers, do not match with requestId. Please try again");

        return true;
    }

    public static boolean validateClientMessage(String clientMessage) {
        if(clientMessage != null && clientMessage.equalsIgnoreCase(Constant.CLIENT_MESSAGE_REQUEST)){
            return true;
        } else {
            throw new InvalidRequestException("Invalid message request. Please try again.");
        }
    }
}
