package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.UserInfo;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: UserInfoDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 20:47
 * @version: 1.0
 */
public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
