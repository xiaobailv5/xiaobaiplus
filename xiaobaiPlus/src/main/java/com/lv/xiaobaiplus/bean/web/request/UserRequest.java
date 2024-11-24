package com.lv.xiaobaiplus.bean.web.request;

import java.io.Serializable;

/**
 * @program: xiaobaiPlus
 * @ClassName UserRequest
 * @description:
 * @author: gxjh
 * @create: 2024-11-24 12:30
 * @Version 1.0
 **/
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -6603216642024316962L;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 开始记录数
     */
    private Integer start;
    /**
     * 每页条数
     */
    private Integer limit;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }

}