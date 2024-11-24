package com.lv.xiaobaiplus.dao.base;

import com.lv.xiaobaiplus.bean.web.Role;

import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 角色dao
 * @date 2023/6/22 12:25:07
 */
public interface RoleDao {

    /**
     * 查询角色信息
     * @param roleId
     * @return
     */
    List<Role> queryRole(Integer roleId);




}
