package com.lv.xiaobaiplus.service;

import com.lv.xiaobaiplus.bean.base.Result;
import com.lv.xiaobaiplus.bean.web.request.LoginRequest;

public interface ILoginService {

    /**
     * 登录
     * @param request
     * @return
     */
    Result login(LoginRequest request);

    /**
     * 退出登录
     * @return
     */
    Result logOut();

}
