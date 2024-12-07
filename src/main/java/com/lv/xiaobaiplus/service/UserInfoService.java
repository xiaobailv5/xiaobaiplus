package com.lv.xiaobaiplus.service;


import com.lv.xiaobaiplus.bean.web.User;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    User getUserInfo(String userId);
    List<Map<String,String>> getAllUserInfo();
}
