package com.lv.xiaobaiplus.service;

import com.lv.xiaobaiplus.bean.web.LoginUser;
import com.lv.xiaobaiplus.bean.web.Permission;
import com.lv.xiaobaiplus.bean.web.Role;
import com.lv.xiaobaiplus.bean.web.User;
import com.lv.xiaobaiplus.dao.base.PermissionDao;
import com.lv.xiaobaiplus.dao.base.RoleDao;
import com.lv.xiaobaiplus.dao.base.UserDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: xiaobaiPlus
 * @ClassName LoginUserDetailsServiceImpl
 * @description:
 * @author: gxjh
 * @create: 2024-11-24 12:15
 * @Version 1.0
 **/
@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findUserByUserName(username);
        if (ObjectUtils.isEmpty(user)){
            throw new RuntimeException("not found user");
        }
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();

        Integer userId = user.getUserId();
        // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        if(ObjectUtils.isNotEmpty(userId)){
            List<Role> roles = roleDao.queryRole(userId);
            if(CollectionUtils.isNotEmpty(roles)){
                for (Role role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
                    //查询权限列表
                    Integer roleId = role.getRoleId();
                    List<Permission> permissions = permissionDao.queryPermission(roleId);
                    if (CollectionUtils.isNotEmpty(permissions)){
                        for (Permission permission : permissions) {
                            authorities.add(new SimpleGrantedAuthority(permission.getPerCode()));
                        }
                    }

                }
            }


        }

        return new LoginUser(user,authorities);
    }

}