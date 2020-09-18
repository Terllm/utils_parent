package com.terllm.jwt.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.terllm.jwt.bean.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

/**
 * @Classname PermissionHandler
 * @Description jwt认证异常统一处理
 * @Date 2020/9/18 16:56
 * @Created by Terllm
 */
@RestControllerAdvice
public class PermissionHandler {


    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public ResponseBean authorizationException(SignatureException e){

        ResponseBean res  = new ResponseBean();
        res.setCode(-1);
        res.setMsg(e.getMessage());
        return res;

    }









}
