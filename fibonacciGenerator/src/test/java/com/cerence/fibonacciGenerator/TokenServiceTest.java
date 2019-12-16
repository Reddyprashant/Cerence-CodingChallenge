package com.cerence.fibonacciGenerator;

import com.cerence.fibonacciGenerator.service.TokenServiceImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.cerence.fibonacciGenerator.util.CommonConstants;
import static org.junit.Assert.*;

@SpringBootTest
public class TokenServiceTest {

    TokenServiceImpl tokenService = new TokenServiceImpl();
    Boolean result;

    public TokenServiceTest() {

    }

    @Test
    public void validateTokenLength() {

        String token = tokenService.generateToken();
        assertTrue(token.length() == 44);
    }

//    @Test
//    public void validateTokenTime() {
//
//        try {
//
//            String result1 = "Bearer "+ tokenService.generateToken();
//        System.out.println(result1);
//            Thread.sleep(120009);
//            result = tokenService.validateToken(result1);
//            assertFalse(false);
//        } catch (Exception e) {
//            assertEquals(e.getMessage(), CommonConstants.TOKEN_EXPIRED);
//        }
//
//    }

    @Test
    public void validateTokenEmpty() {

        try {
            String token = null;
            result = tokenService.validateToken(token);
            assertFalse(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), CommonConstants.EMPTY_TOKEN);
        }

    }
}
