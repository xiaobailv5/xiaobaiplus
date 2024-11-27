package com.lv.xiaobaiplus.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.xiaobaiplus.bean.base.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功拦截器
 */
@Component
public class SuccessHandlerImpl implements AuthenticationSuccessHandler {
    ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功");
        response.setCharacterEncoding("UTF-8"); //设置输出信息的编码格式
        Object user=  authentication.getPrincipal(); //获取登录用户的主体
        System.out.println("========="+user.toString()); //这里打印测试下是不是我们要的信息
        PrintWriter wirter = response.getWriter();

        //下面的objectMapper是jackson中转json的一个对象
        if(objectMapper == null){
            objectMapper  = new ObjectMapper();
        }
        Result result =new Result();
        result.setCode(0);
        result.setMessage("登录成功");
       // result.setData();
        wirter.println(objectMapper.writeValueAsString(result));  //然他给屏幕上打印我们的json
    }
}
