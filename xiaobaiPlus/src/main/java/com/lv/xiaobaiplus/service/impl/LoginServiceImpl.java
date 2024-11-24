package com.lv.xiaobaiplus.service.impl;

import com.lv.xiaobaiplus.bean.base.Result;
import com.lv.xiaobaiplus.bean.base.ResultGenerator;
import com.lv.xiaobaiplus.bean.common.Constant;
import com.lv.xiaobaiplus.bean.web.LoginUser;
import com.lv.xiaobaiplus.bean.web.request.LoginRequest;
import com.lv.xiaobaiplus.service.ILoginService;
import com.lv.xiaobaiplus.util.JwtUtil;
import com.lv.xiaobaiplus.util.RedisUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: xiaobaiPlus
 * @ClassName LoginServiceImpl
 * @description:
 * @author: gxjh
 * @create: 2024-11-24 13:34
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Result login(LoginRequest request) {
        //用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证不通过
        if(ObjectUtils.isEmpty(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //认证通过 用userId生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = String.valueOf(loginUser.getUser().getUserId());
        String jwt = JwtUtil.createJWT(userId);
        //user信息存入redis 5分钟
        redisUtil.setObject(Constant.LOGIN+userId,loginUser,300, TimeUnit.SECONDS);
        Map<String,Object> map = new HashMap<>(2);
        map.put("token",jwt);
        return ResultGenerator.getSuccessBeanResult(map);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Result logOut() {
        //获取SecurityContextHolder中的 用户id
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = loginUser.getUser().getUserId();
        //删除redis中的用户
        String redisKey = Constant.LOGIN+userId;
        redisUtil.deleteObject(redisKey);

        return ResultGenerator.getSuccessResult();
    }


}