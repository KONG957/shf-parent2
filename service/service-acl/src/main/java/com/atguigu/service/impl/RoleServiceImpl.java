package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @projectName: shf-parent
 * @package: com.atguigu.service.impl
 * @className: RoleServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/15 19:23
 * @version: 1.0
 */
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    protected BaseDao getEntityDao() {
        return roleDao;
    }

    @Override
    public List<Role> findAll() {
       return roleDao.findAll();
    }
}
