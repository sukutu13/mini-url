package com.miniurl.miniurldemo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.miniurl.miniurldemo.entity.Url;

@Component
public class MD5Util {

    /**
     * Returns an hash code using the MD5 from the long url received
     * @param url - Url - Object containing long url 
     * @return hash code from long url
     * @throws NoSuchAlgorithmException
     */
    public String generateMiniUrl(Url url) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(url.getBigUrl().getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);

        return hashtext;
    }

}
