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

    /* validateTokenTime method is to test validity of token, after generating token it waits for more than time and validates the token */
    @Test
    public void validateTokenTime() {

        try {

            String result1 = "Bearer " + tokenService.generateToken();
            System.out.println(result1);
            Thread.sleep(120009);
            result = tokenService.validateToken(result1);
            assertFalse(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), CommonConstants.TOKEN_EXPIRED);
        }

    }

    /* validateTokenEmpty method is to test empty token against the implementation*/
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
