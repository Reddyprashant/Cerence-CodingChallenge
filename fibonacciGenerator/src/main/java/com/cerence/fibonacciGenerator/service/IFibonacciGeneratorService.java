package com.cerence.fibonacciGenerator.service;


import java.util.List;
import org.springframework.stereotype.Service;



public interface IFibonacciGeneratorService {


    public List<Integer> generateFibonacciSequence(int number);
}
