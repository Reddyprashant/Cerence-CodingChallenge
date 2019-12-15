package com.cerence.fibonacciGenerator;

import com.cerence.fibonacciGenerator.controller.FibonacciGeneratorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FibonacciGeneratorApplication {

    private static Logger logger = LogManager.getLogger(FibonacciGeneratorApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(FibonacciGeneratorApplication.class, args);
        logger.error("Didn't do it.");
    }

}
