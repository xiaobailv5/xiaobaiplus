package com.lv.xiaobaiplus.service.impl;


import com.lv.xiaobaiplus.bean.web.User;
import com.lv.xiaobaiplus.dao.base.UserInfoDao;
import com.lv.xiaobaiplus.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
@Transactional()
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoDao userInfoDao;

    @Override
    public User getUserInfo(String userId) {

        return userInfoDao.getUserInfo(userId);
    }

    @Override
    public List<Map<String,String>> getAllUserInfo() {
        return userInfoDao.getAllUserInfo();
    }
}
