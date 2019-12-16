package com.cerence.fibonacciGenerator.service;

import com.cerence.fibonacciGenerator.exception.BadRequest;
import org.springframework.stereotype.Service;
import com.cerence.fibonacciGenerator.util.CommonConstants;

import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciGeneratorServiceImpl implements IFibonacciGeneratorService {


    /* This method takes input Integer number n and return first n fibonacci sequence */
    @Override
    public List<Integer> generateFibonacciSequence(int number) throws BadRequest {

        int firstNum = 0;
        int secondNum = 1;
        ArrayList<Integer> result = new ArrayList<Integer>();

        /*checking whether input number is between 1 and 2000 */
        if (number < 1 || number > 2000) {

            throw new BadRequest(CommonConstants.BAD_REQUEST_MESSAGE);
        }

        result.add(firstNum);
        /*checking whether input number is 1 and return result to controller, if number is not 1 then we generate first n fibonacci sequence by iterating till the input number */
        if (number == 1) {
            return result;
        } else {
            result.add(secondNum);
            for (int i = 2; i < number; i++) {
                result.add(result.get(i - 2) + result.get(i - 1));

            }
        }
        return result;
    }


}
