package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: RoleDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/15 19:24
 * @version: 1.0
 */
@Repository
public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

//    Integer insert(Role role);

//    Role foundAllRole(Long id);

//    Integer updateRole(Role role);

//    Integer deleteRole(Long id);

//    Page<Role> findPage(Map<String,Object> filters);
}
