package com.cerence.fibonacciGenerator.service;

import org.springframework.stereotype.Service;

@Service
public interface ITokenService {


	public String generateToken();
    public boolean validateToken(String token);

    public boolean authorize(String token);
}
