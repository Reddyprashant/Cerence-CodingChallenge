package com.cerence.fibonacciGenerator;


import com.cerence.fibonacciGenerator.service.FibonacciGeneratorServiceImpl;
import com.cerence.fibonacciGenerator.service.IFibonacciGeneratorService;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.cerence.fibonacciGenerator.util.CommonConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@SpringBootTest
public class FibonacciGeneratorApplicationTests {

    public FibonacciGeneratorApplicationTests() {

    }

    List<Integer> result = new ArrayList<Integer>();
    int number;
    IFibonacciGeneratorService fibonacciGeneratorService = new FibonacciGeneratorServiceImpl();


    @Test
    public void generateFibonacciSequenceNegativeInput() {
        number = -2;
        try {

            result = fibonacciGeneratorService.generateFibonacciSequence(number);
            assertFalse(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), CommonConstants.BAD_REQUEST_MESSAGE);
        }

    }

    @Test
    public void generateFibonacciSequenceZeroInput() {
        number = 0;
        try {

            result = fibonacciGeneratorService.generateFibonacciSequence(number);
            assertFalse(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), CommonConstants.BAD_REQUEST_MESSAGE);
        }

    }

    @Test
    public void generateFibonacciSequenceGreaterThanZeroInput() {
        number = 10;
        List<Integer> expResult = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        result = fibonacciGeneratorService.generateFibonacciSequence(number);
        assertEquals(expResult, result);
    }


}
