package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: PermissionDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/29 23:35
 * @version: 1.0
 */
public interface PermissionDao extends BaseDao<Permission> {

     List<Permission> findAll();
     List<Permission> findListByAdminId (Long adminId);

    List<String> findAllCodeList();

    List<String> findCodeListByAdminId(Long adminId);
}
