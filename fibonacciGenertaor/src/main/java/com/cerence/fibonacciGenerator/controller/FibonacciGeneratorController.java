package com.cerence.fibonacciGenerator.controller;


import com.cerence.fibonacciGenerator.exception.BadRequest;
import com.cerence.fibonacciGenerator.service.FibonacciGeneratorService;
import com.cerence.fibonacciGenerator.service.FibonacciGeneratorServiceImpl;
import com.cerence.fibonacciGenerator.service.TokenService;
import com.cerence.fibonacciGenerator.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class FibonacciGeneratorController {

    FibonacciGeneratorService fibonacciGeneratorService;
    TokenService tokenService;

    @Autowired
    public FibonacciGeneratorController() {

        this.fibonacciGeneratorService = new FibonacciGeneratorServiceImpl();
        this.tokenService = new TokenServiceImpl();
    }


    private HashMap<String, String> map = new HashMap<>();

    @GetMapping("/fibonacci/{number}")
    public ResponseEntity <List<Integer>> getFibonnaciNumbers(@PathVariable(name="number", required = true) Integer number, @RequestHeader HttpHeaders requestHeaders){

        String token = requestHeaders.getFirst("Authorization");

        if(!(tokenService.validateToken(token))){
            map.clear();
            map.put("message: ", "Token is expired");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(number < 1){
            map.clear();
            map.put("message: ", "Please Enter the number greater than 0 to get Fibonacci sequence ");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        else {
            List<Integer> generatedSequence = fibonacciGeneratorService.generateFibonacciSequence(number);
            return new ResponseEntity<>(generatedSequence , HttpStatus.OK);
        }
    }
}
