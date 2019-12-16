package com.cerence.fibonacciGenerator.controller;


import com.cerence.fibonacciGenerator.service.IFibonacciGeneratorService;
import com.cerence.fibonacciGenerator.service.ITokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cerence.fibonacciGenerator.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class FibonacciGeneratorController {


    private final IFibonacciGeneratorService iFibonacciGeneratorService;
    private final ITokenService iTokenService;

    private static final Logger logger = LogManager.getLogger(FibonacciGeneratorController.class);

    /* Constructor injection */
    @Autowired
    public FibonacciGeneratorController(final IFibonacciGeneratorService iFibonacciGeneratorService, final ITokenService iTokenService) {
        this.iFibonacciGeneratorService = iFibonacciGeneratorService;
        this.iTokenService = iTokenService;
    }

    /* HealthCheck method is to verify whether out service is up and running */
    @GetMapping(path = "v1/fibonacci/")
    public ResponseEntity<String> HealthCheck(@RequestHeader HttpHeaders requestHeaders) {
        return new ResponseEntity<>("Fibonacci series RESTFUL service is up and running", HttpStatus.OK);
    }


    /* This method takes input as integer number n , validates the token and returns first n fibonacci numbers from generateFibonacciSequence method implemented in iFibonacciGeneratorService service class */
    @GetMapping(path = "v1/fibonacci/{number}")
    public ResponseEntity<String> getFibonnaciNumbers(@PathVariable(name = "number", required = true) String number, @RequestHeader HttpHeaders requestHeaders) {

        String token = requestHeaders.getFirst("Authorization");
        List<Integer> generatedSequence = new ArrayList<>();
        try {
            if ((iTokenService.validateToken(token))) {
                Integer num = Integer.parseInt(number);
                generatedSequence = iFibonacciGeneratorService.generateFibonacciSequence(num);
            }
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(CommonConstants.NUMBER_EXCEPTION, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException ex) {

            if (ex.getMessage().equals(CommonConstants.EMPTY_TOKEN)) {
                logger.debug(ex.getMessage());
                return new ResponseEntity<>(CommonConstants.EMPTY_TOKEN, HttpStatus.BAD_REQUEST);
            } else if (ex.getMessage().equals(CommonConstants.INVALID_FORMAT)) {
                logger.error(ex.getMessage());
                return new ResponseEntity<>(CommonConstants.INVALID_FORMAT, HttpStatus.BAD_REQUEST);
            } else if (ex.getMessage().equals(CommonConstants.TOKEN_EXPIRED)) {
                logger.error(ex);
                return new ResponseEntity<>(CommonConstants.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
            } else if (ex.getMessage().equals(CommonConstants.BAD_REQUEST_MESSAGE)) {
                logger.error(ex.getMessage());
                return new ResponseEntity<>(CommonConstants.BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
            } else {
                logger.error(ex.getMessage());
                return new ResponseEntity<>(CommonConstants.BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(String.valueOf(generatedSequence), HttpStatus.OK);
    }

}
