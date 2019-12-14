package com.cerence.fibonacciGenerator.service;

import com.cerence.fibonacciGenerator.exception.BadRequest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGeneratorServiceImpl implements FibonacciGeneratorService {


  @Override
    public  List<Integer> generateFibonacciSequence(int number){
        int firstNum = 0;
        int secondNum = 1;
      ArrayList<Integer> result = new ArrayList<Integer>();


      if(number == 1){
          result.add(secondNum);
      }
      if(number ==0){
           ;
      }
        for (int i =2; i < number; i++){
            secondNum = firstNum + secondNum;
            firstNum = secondNum - firstNum;
   result.add(secondNum);

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
