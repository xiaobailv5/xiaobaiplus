package com.lv.xiaobaiplus.dao.base;

import com.lv.xiaobaiplus.bean.web.Permission;

import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 权限PermissionDao
 * @date 2023/6/22 12:44:56
 */
public interface PermissionDao {


    List<Permission> queryPermission(Integer roleId);
}
