package com.lv.xiaobaiplus.dao.base;

import com.lv.xiaobaiplus.bean.web.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoDao {
    User getUserInfo(@Param("userId") String userId);
    List<Map<String,String>> getAllUserInfo();

}
