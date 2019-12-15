package com.cerence.fibonacciGenerator.controller;


import com.cerence.fibonacciGenerator.service.IFibonacciGeneratorService;
import com.cerence.fibonacciGenerator.service.ITokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CommonConstants;
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

    private static Logger logger = LogManager.getLogger(FibonacciGeneratorController.class);

    @Autowired
    public FibonacciGeneratorController(final IFibonacciGeneratorService iFibonacciGeneratorService, final ITokenService iTokenService) {

        this.iFibonacciGeneratorService = iFibonacciGeneratorService;
        this.iTokenService = iTokenService;
    }


    @GetMapping(path = "v1/fibonacci/{number}")
    public ResponseEntity<String> getFibonnaciNumbers(@PathVariable(name = "number", required = true) Integer number, @RequestHeader HttpHeaders requestHeaders) {
        logger.error("logging msgs");
        String token = requestHeaders.getFirst("Authorization");
        List<Integer> generatedSequence = new ArrayList<>();
        try {
            if ((iTokenService.validateToken(token))) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                generatedSequence = iFibonacciGeneratorService.generateFibonacciSequence(number);


            }

        } catch (RuntimeException ex) {
            // TODO: handle exception
            if (ex.getMessage().equals(CommonConstants.EMPTY_TOKEN)) {
                logger.debug(ex);
                return new ResponseEntity<>(CommonConstants.EMPTY_TOKEN, HttpStatus.BAD_REQUEST);

            } else if (ex.getMessage().equals(CommonConstants.INVALID_FORMAT)) {
                logger.error(ex);
                return new ResponseEntity<>(CommonConstants.INVALID_FORMAT, HttpStatus.BAD_REQUEST);
            } else if (ex.getMessage().equals(CommonConstants.TOKEN_EXPIRED)) {
                logger.error(ex);
                return new ResponseEntity<>(CommonConstants.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
            } else if (ex.getMessage().equals(CommonConstants.BAD_REQUEST_MESSAGE)) {
                logger.error(ex);
                return new ResponseEntity<>(CommonConstants.BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(String.valueOf(generatedSequence), HttpStatus.OK);

    }

}
