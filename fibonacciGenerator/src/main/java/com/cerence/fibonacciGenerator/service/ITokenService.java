package com.cerence.fibonacciGenerator.service;


public interface ITokenService {


    String generateToken();

     boolean validateToken(String token);

     boolean authorize(String token);
}
