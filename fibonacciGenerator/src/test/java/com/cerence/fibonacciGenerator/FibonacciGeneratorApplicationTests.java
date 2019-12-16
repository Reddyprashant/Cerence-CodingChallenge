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


    /* generateFibonacciSequenceNegativeInput method is to test negative input against the implementation*/
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

    /* generateFibonacciSequenceZeroInput method is to test zero input against the implementation*/
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

    /* generateFibonacciSequenceLargerInput method is to test input greater than 2000 against the implementation */
    @Test
    public void generateFibonacciSequenceLargerInput() {
        number = 2200;
        try {

            result = fibonacciGeneratorService.generateFibonacciSequence(number);
            assertFalse(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), CommonConstants.BAD_REQUEST_MESSAGE);
        }

    }

    /* generateFibonacciSequenceValidInput method is to test input between 1 and 2000 and generate first n fibonacci sequence */
    @Test
    public void generateFibonacciSequenceValidInput() {
        number = 10;
        List<Integer> expResult = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        result = fibonacciGeneratorService.generateFibonacciSequence(number);
        assertEquals(expResult, result);
    }


}
