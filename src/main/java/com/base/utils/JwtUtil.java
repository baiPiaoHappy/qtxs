package com.base.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


import java.util.Date;


/**
 * @description: token操作相关
 * @author: 小猴子
 * @date: 2020-05-18 16:27
 */
@Data
@Component
@ConfigurationProperties( prefix = "bl.jwt")
public class JwtUtil {

    private Long exprie;

    private String secret;

    private String header;

    //private SecretKey  key = null;


    private SecretKey initKey(SecretKey key){
        if(key == null){
            key = Keys.hmacShaKeyFor( secret.getBytes());
        }
        return key;
    }


    /**
     *  获取token
     * @param userId
     * @return
     */
    public String getToken(String userId){
        Date now = new Date();
        Date ex = new Date(now.getTime() + exprie * 1000);
        SecretKey key = Keys.hmacShaKeyFor( secret.getBytes());
        String token = Jwts.builder()
                            //.setHeaderParam()
                            .setSubject(userId)
                            .setIssuedAt(now)
                            .setExpiration(ex)
                            .signWith(key,SignatureAlgorithm.HS256)
                            .compact();
        return token;
    }

    /**
     *  解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
        SecretKey key = Keys.hmacShaKeyFor( secret.getBytes());
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean isTokenExpired (Date expiredTime){
        //expiprationTime
        return expiredTime.before(new Date());
    }

}
