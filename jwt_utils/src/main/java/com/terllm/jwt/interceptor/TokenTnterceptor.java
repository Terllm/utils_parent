package com.terllm.jwt.interceptor;

import com.terllm.jwt.annotatio.IsAuthentication;
import com.terllm.jwt.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

/**
 * @Classname TokenTnterceptor
 * @Description token 校验拦截器
 * @Date 2020/9/18 15:57
 * @Created by Terllm
 */
@Component
@Slf4j
public class TokenTnterceptor extends HandlerInterceptorAdapter {


    @Resource
    JwtConfig jwtConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //判断是否需要校验认证
        if(handler instanceof HandlerMethod){

            HandlerMethod hm = (HandlerMethod) handler;
            IsAuthentication annotation = hm.getMethodAnnotation(IsAuthentication.class);

            if(annotation !=null && annotation.value()){
                return true;
            }

        }

        // 在消息头中获取token
        String token = request.getHeader(jwtConfig.getHeader());

        if (StringUtils.isEmpty(token)) {
            //在参数中获取 token
            token = request.getParameter(jwtConfig.getHeader());
        }


        try {
            if (StringUtils.isEmpty(token) || jwtConfig.isTokenExpired(token)) {
                //在参数中获取 token
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新认证。");
            }
        } catch (SignatureException e) {

            throw new SignatureException("会话未认证，请认证");
        }


        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
