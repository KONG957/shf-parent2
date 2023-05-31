package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: AdminService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 17:18
 * @version: 1.0
 */
public interface AdminService extends BaseService<Admin> {

    List<Admin> findAll();

     Map<String,Object> findRoleByAdminId(Long id);

    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);

    Admin getByUsername(String username);
}
