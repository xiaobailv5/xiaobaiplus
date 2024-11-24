package com.lv.xiaobaiplus.bean.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @program: xiaobaiPlus
 * @ClassName LoginUser
 * @description:
 * @author: gxjh
 * @create: 2024-11-24 12:34
 * @Version 1.0
 **/
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 6001564138142758556L;

    public LoginUser(User user, List<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    private User user;

    List<GrantedAuthority> authorities;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginUser(User user) {
        this.user = user;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }
    
}