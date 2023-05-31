package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.UserInfoDao;
import com.atguigu.entity.UserInfo;
import com.atguigu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: UserInfoServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 20:49
 * @version: 1.0
 */
@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    protected BaseDao getEntityDao() {
        return userInfoDao;
    }

    @Override
    public UserInfo getByPhone(String phone) {
       return userInfoDao.getByPhone(phone);
    }
}
