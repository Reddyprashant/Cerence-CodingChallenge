package com.cerence.fibonacciGenerator.service;

import com.cerence.fibonacciGenerator.exception.BadRequest;
import com.cerence.fibonacciGenerator.exception.Unauthorized;
import com.cerence.fibonacciGenerator.util.CommonConstants;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenServiceImpl implements ITokenService {

    private static String finalKey = "abcdefqwertyuiop";
    String finalToken;

    /* generateToken method generates the authentication token using AES encryption algorithm */
    @Override
    public String generateToken() {

        try {
            String initVector = "RandomInitVector";
            JSONObject object = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 2);
            Date date = calendar.getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            object.put("ttl", df.format(date));
            String token = object.toString();
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(finalKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(token.getBytes()); /* encrypting token */
            finalToken = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(encrypted);   /* encoded token (Base64 encoding)*/

        } catch (Exception e) {
            throw new BadRequest(CommonConstants.INVALID_TOKEN_GENERATION);
        }
        return finalToken;
    }

    /* validateToken takes input  token and  validates the token with different scenarios and sends appropriate response back to controller */
    @Override
    public boolean validateToken(String token) throws RuntimeException {
        if (token == null || token.isEmpty()) {
            throw new BadRequest(CommonConstants.EMPTY_TOKEN);
        }
        String token1 = "";
        if (!token.contains("Bearer ")) {
            throw new BadRequest(CommonConstants.INVALID_FORMAT);
        }
        token1 = token.substring(7);
        System.out.println("token value is " + token1);
        if (!authorize(token1)) {
            throw new Unauthorized(CommonConstants.TOKEN_EXPIRED);
        }
        return true;
    }

    /* authorize takes input token and authorizes the token using AES Decryption algorithm, it also checks for token time validity and send appropriate response back to controller */
    @Override
    public boolean authorize(String token) {
        JSONParser parser = new JSONParser();
        try {
            String initVector = "RandomInitVector";
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(finalKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(token));
            String entityDecoded = new String(original);
            org.json.simple.JSONObject jsonobj = (org.json.simple.JSONObject) parser.parse(entityDecoded);
            Object arrayOfTests = jsonobj.get("ttl");
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String getDate = arrayOfTests.toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date end = formatter.parse(getDate);
            Date start = formatter.parse(formatter.format(date));
            if (!start.before(end)) {

                return false;
            }
        } catch (Exception e) {
            System.out.println("inside exception---" + e);
            return false;
        }
        return true;
    }
}
