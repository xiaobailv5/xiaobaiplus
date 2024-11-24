package com.lv.xiaobaiplus.dao.base;


import com.lv.xiaobaiplus.bean.web.User;
import com.lv.xiaobaiplus.bean.web.request.UserRequest;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findUserByUserName(String username);

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    List<User> getUserList(UserRequest request);

    /**
     * 查询用户列表总条数
     * @param request
     * @return
     */
    int getUserCount(UserRequest request);

    User getUserInfo(String userId);
}
