package com.ecom.productservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JwtUtil
{

    private static String secretKey;
    JwtUtil(){
        SecureRandom random=new SecureRandom();
        byte[] key =new byte[32];//256bits
        random.nextBytes(key);
        secretKey= Base64.getEncoder().encodeToString(key);
    }

    private Key getSignedKey()
    {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String username, List<String> roles)
    {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles",roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*2))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }


}
