package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Role;

import java.util.List;


/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: RoleService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/15 19:22
 * @version: 1.0
 */
public interface RoleService extends BaseService<Role>{

    List<Role> findAll();

//    Integer insert(Role role);
//
//    Role foundAllRole(Long id);
//
//    Integer updateRole(Role role);
//
//    void deleteRole(Long id);
//
//    PageInfo<Role> findPage(Map<String,Object> filters);


}
