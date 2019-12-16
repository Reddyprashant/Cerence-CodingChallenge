package com.cerence.fibonacciGenerator;

import com.cerence.fibonacciGenerator.controller.FibonacciGeneratorController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

public class FibonacciGeneratorApplication {


    private static final Logger logger = LogManager.getLogger(FibonacciGeneratorController.class);
    public static void main(String[] args) {

        SpringApplication.run(FibonacciGeneratorApplication.class, args);

    }

}
