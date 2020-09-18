package com.terllm.jwt.controller;

import com.terllm.jwt.annotatio.IsAuthentication;
import com.terllm.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname DemoController
 * @Description 测试入口
 * @Date 2020/9/18 16:27
 * @Created by Terllm
 */
@RestController
public class DemoController {


    @Autowired
    JwtConfig jwtConfig;


    @RequestMapping("demo")
    @IsAuthentication(true)
    public String demo1(){


        return "Hello World !!!";
    }



    @RequestMapping("Premission")
    @IsAuthentication(true)
    public String getPremission(String subject){

        return jwtConfig.createToken(subject);


    }

    @RequestMapping("getSubject")

    public Claims getSubject(String token){

        return jwtConfig.getTokenClaim(token);

    }








}
