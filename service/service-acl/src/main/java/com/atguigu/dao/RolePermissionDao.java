package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.RolePermission;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: RolePermissionDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/30 14:30
 * @version: 1.0
 */
public interface RolePermissionDao extends BaseDao<RolePermission> {

    List<Long> findPermissionIdListByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
