package com.lv.xiaobaiplus.controller;

import com.lv.xiaobaiplus.bean.base.Result;
import com.lv.xiaobaiplus.bean.base.ResultGenerator;
import com.lv.xiaobaiplus.bean.web.request.LoginRequest;
import com.lv.xiaobaiplus.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 登录 控制层
 * @date 2023/6/18 15:05:44
 */
@RestController
public class LoginUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserController.class);

    @Resource
    private ILoginService loginService;

    /**
     * 登录接口
     * @param request
     * @return com.example.lv.bean.base.Result
     * @author gxjh2
     * @date 2024/10/25 16:54:58
    */
    @PostMapping("/user/login")
//    @I18nAnnotation(replaceKey = "username", returnType = "bean", name = "java.util.Map")
    public Result login(@RequestBody LoginRequest request){
        LOGGER.info("登录======开始"+request);
        return loginService.login(request);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/user/logOut")
    public Result logOut(){

        Result result;
        try {
            return loginService.logOut();
        }catch (Exception e){
            e.printStackTrace();
            result = ResultGenerator.getFailResult(e.getMessage());
        }
        return result;
    }

    @GetMapping("/user/test")
    public Result test() throws InterruptedException {
        Thread.sleep(1000);
        return ResultGenerator.getSuccessResult("测试成功");
    }
}
