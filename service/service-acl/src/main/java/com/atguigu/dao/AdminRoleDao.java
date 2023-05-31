package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.AdminRole;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: AdminRoleDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/29 20:06
 * @version: 1.0
 */
public interface AdminRoleDao extends BaseDao<AdminRole> {


    List<Long> findRoleIdByAdminId(Long id);

    Integer deleteByAdminId(Long adminId);
}
