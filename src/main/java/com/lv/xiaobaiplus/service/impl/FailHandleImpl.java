package com.lv.xiaobaiplus.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.xiaobaiplus.bean.base.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败拦截器
 */
@Component
public class FailHandleImpl implements AuthenticationFailureHandler {
    ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("登录失败");
        response.setCharacterEncoding("UTF-8"); //设置输出信息的编码格式
        PrintWriter wirter = response.getWriter();
        if(objectMapper == null){
            objectMapper  = new ObjectMapper();
        }
        Result resultVO = new Result();
        resultVO.setCode(-9999);
        resultVO.setMessage("登录失败，用户名或密码错误");
        wirter.println(objectMapper.writeValueAsString(resultVO));

    }
}