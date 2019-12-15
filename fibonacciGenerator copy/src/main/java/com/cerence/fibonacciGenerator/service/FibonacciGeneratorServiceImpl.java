package com.cerence.fibonacciGenerator.service;

import com.cerence.fibonacciGenerator.exception.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import util.CommonConstants;

import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciGeneratorServiceImpl implements IFibonacciGeneratorService {


  @Override
    public  List<Integer> generateFibonacciSequence(int number) throws BadRequest{
        
	  int firstNum = 0;
      int secondNum = 1;
      ArrayList<Integer> result = new ArrayList<Integer>();

          if(number<1){

              throw new BadRequest(CommonConstants.BAD_REQUEST_MESSAGE);
          }
      result.add(firstNum);   
      if(number == 1){
    	  return result;
      }
      else {
    	  result.add(secondNum);
        for (int i =2; i < number; i++){       	
        	result.add(result.get(i-2) + result.get(i-1));

        }
      }


   return result;
    }

    public HttpStatus getStatus(Exception ex) {
        if (ex instanceof BadRequest) {
            BadRequest runex = (BadRequest) ex;
            return runex.getStatus();
        }else
            return null;
    }
    }
