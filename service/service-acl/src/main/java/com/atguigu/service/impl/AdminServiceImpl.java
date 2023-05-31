package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseService;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.AdminDao;
import com.atguigu.dao.AdminRoleDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Admin;
import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Role;
import com.atguigu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @projectName: shf-parent
 * @package: com.atguigu.service.impl
 * @className: AdminServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 17:18
 * @version: 1.0
 */
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AdminRoleDao adminRoleDao;
    @Override
    protected BaseDao getEntityDao() {
        return adminDao;
    }

    @Override
    public List<Admin> findAll() {

      return  adminDao.findAll();
    }

    @Override
    public Map<String,Object> findRoleByAdminId(Long id) {
        List<Long> roleIdList = adminRoleDao.findRoleIdByAdminId(id);
        List<Role> roles = roleDao.findAll();
        List<Role> noAssginRoleList = new ArrayList<>();
        List<Role> assginRoleList = new ArrayList<>();

        for (Role role : roles) {
           if(roleIdList.contains(role.getId())){
               assginRoleList.add(role);
           }else {
               noAssginRoleList.add(role);
           }
        }
        HashMap<String, Object> roleMap = new HashMap<>();
        roleMap.put("noAssginRoleList",noAssginRoleList);
        roleMap.put("assginRoleList",assginRoleList);
        return roleMap;


    }

    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        adminRoleDao.deleteByAdminId(adminId);
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);

        for (Long roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            adminRole.setRoleId(roleId);
            adminRoleDao.insert(adminRole);
        }
    }

    @Override
    public Admin getByUsername(String username) {
       Admin admin = adminDao.getByUsername(username);
       return admin;
    }
}
