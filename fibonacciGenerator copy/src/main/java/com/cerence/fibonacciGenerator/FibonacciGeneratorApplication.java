package com.cerence.fibonacciGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan("com.cerence")
public class FibonacciGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FibonacciGeneratorApplication.class, args);
	}

}
