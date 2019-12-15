package com.cerence.fibonacciGenerator.service;

import com.cerence.fibonacciGenerator.exception.BadRequest;
import com.cerence.fibonacciGenerator.exception.Unauthorized;
import util.CommonConstants;
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


            //Partial token created
            String token = object.toString();
            System.out.println("Token values is " + token);
            System.out.println("TTL is : " + object.get("ttl"));


            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(finalKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            //encrypting token
            byte[] encrypted = cipher.doFinal(token.getBytes());

            // encoded token (Base64 encoding)
            finalToken = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(encrypted);

        } catch (Exception e) {
            // TODO: handle exception
            throw new BadRequest(CommonConstants.INVALID_TOKEN_GENERATION);
        }
        return finalToken;
    }

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

    @Override
    public boolean authorize(String token) {
        JSONParser parser = new JSONParser();
        try {
            System.out.println("token coming in authorize" + token);
            String initVector = "RandomInitVector";
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(finalKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(token));
            String entityDecoded = new String(original);

            System.out.println("***Entity Decoded is " + entityDecoded);

            org.json.simple.JSONObject jsonobj = (org.json.simple.JSONObject) parser.parse(entityDecoded);
            Object arrayOfTests = jsonobj.get("ttl");
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String getDate = arrayOfTests.toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            Date end = formatter.parse(getDate);
            Date start = formatter.parse(formatter.format(date));

            System.out.println(start.toString());
            System.out.println(end.toString());

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
