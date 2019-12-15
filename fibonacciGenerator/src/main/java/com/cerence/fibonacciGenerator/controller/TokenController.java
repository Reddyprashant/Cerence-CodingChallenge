package com.cerence.fibonacciGenerator.controller;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cerence.fibonacciGenerator.service.ITokenService;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@RestController
public class TokenController {


    private final ITokenService iTokenService;
    
    @Autowired
    public TokenController(final ITokenService iTokenService) {

        this.iTokenService = iTokenService;
    }
    
    @GetMapping("v1/token")
    public ResponseEntity<String> getToken(@RequestHeader HttpHeaders headers) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, JSONException{

    	String token =iTokenService.generateToken();
    	  	
    	return new ResponseEntity<>(token, HttpStatus.CREATED);


    }
}
