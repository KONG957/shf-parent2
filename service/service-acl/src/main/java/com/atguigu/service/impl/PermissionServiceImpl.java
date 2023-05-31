package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import com.atguigu.helper.PermissionHelper;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service.impl
 * @className: PermissionServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/29 23:34
 * @version: 1.0
 */

@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    protected BaseDao getEntityDao() {
        return permissionDao;
    }

    @Override
    public List<Map<String, Object>> findPermissionByRoleId(Long roleId) {

        List<Permission> permissionList = permissionDao.findAll();
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdListByRoleId(roleId);
        List<Map<String, Object>> zNodes = new ArrayList<>();

        for (Permission permission : permissionList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put(("name"),permission.getName());
            if(permissionIdList.contains(permission.getId())){
                map.put("checked", true);
            }
            zNodes.add(map);
        }

        return zNodes;
    }

    @Override
    public void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds) {

        rolePermissionDao.deleteByRoleId(roleId);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        for (Long permissionId : permissionIds) {
            rolePermission.setPermissionId(permissionId);
            rolePermissionDao.insert(rolePermission);
        }

    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        if(adminId.longValue()==1){
            List<Permission> permissionList = permissionDao.findAll();
            List<Permission> result = PermissionHelper.bulid(permissionList);
            return result;
        }
        List<Permission> permissionList = permissionDao.findListByAdminId(adminId);
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }

    @Override
    public List<String> findCodeListByAdminId(Long adminId) {
        if(adminId.longValue()==1){
            List<String> codeList =  permissionDao.findAllCodeList();
            return codeList;
        }
            List<String> codeList = permissionDao.findCodeListByAdminId(adminId);
        return codeList;

    }
}
