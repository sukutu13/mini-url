package com.miniurl.miniurldemo.handler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.miniurl.miniurldemo.entity.Url;

@Component
public class GenerateMiniUrlHandler {

    public String generateMiniUrl(Url url) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(url.getBigUrl().getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);

        return hashtext;
    }

}
