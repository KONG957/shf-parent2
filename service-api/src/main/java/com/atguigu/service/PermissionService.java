package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: PermissionService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/29 23:32
 * @version: 1.0
 */
public interface PermissionService extends BaseService<Permission> {


    List<Map<String,Object>> findPermissionByRoleId(Long roleId);

    void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds);

     List<Permission> findMenuPermissionByAdminId(Long adminId);

     List<String> findCodeListByAdminId(Long adminId);
}
