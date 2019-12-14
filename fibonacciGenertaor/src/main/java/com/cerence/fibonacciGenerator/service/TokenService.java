package com.cerence.fibonacciGenerator.service;

public interface TokenService {


    public boolean validateToken(String token);

    public boolean authorize(String token);
}
