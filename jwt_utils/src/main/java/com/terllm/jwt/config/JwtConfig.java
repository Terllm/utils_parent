package com.terllm.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @Classname JwtConfig
 * @Description JWT配置信息
 * @Date 2020/9/18 11:57
 * @Created by Terllm
 */
@ConfigurationProperties(prefix = "config.jwt")
@Configuration
@Data
@Setter
@Getter
@Slf4j
public class JwtConfig {

    private String secret;
    private long expire;
    private String header;


    /**
    * @Description 生成token
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public String createToken(String subject) {

        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expire * 1000);

        String token = Jwts.builder().setHeaderParam("typ", "JWT")
                        .setSubject(subject)
                        .setIssuedAt(now)
                        .setExpiration(expireTime)
                        .signWith(SignatureAlgorithm.HS512, secret)
                        .compact();
        return token;

    }


    /**
    * @Description 获取token中注册信息
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public Claims getTokenClaim(String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

    /**
    * @Description 验证token是否在有效期内
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public boolean isTokenExpired(String token){


        return  getTokenClaim(token).getExpiration().before(new Date());
    }


    /**
    * @Description 获取token失效时间
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public Date getExpirationDateFromToken(String token){

        return getTokenClaim(token).getExpiration();

    }


    /**
    * @Description 从token中获取用户信息
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public String getUserNameFromToken(String token){

        return getTokenClaim(token).getSubject();

    }

    /**
    * @Description 获取jwt发布时间
    * @param
    * @return
    * @Date 2020/9/18
    * @auther Terllm
    */
    public Date getIssuedAtDateFromToken(String token){

        return getTokenClaim(token).getIssuedAt();

    }









}
