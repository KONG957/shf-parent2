package com.atguigu.service;

import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseService;
import com.atguigu.entity.UserInfo;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: UserInfoService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 20:49
 * @version: 1.0
 */
public interface UserInfoService extends BaseService<UserInfo> {

    UserInfo getByPhone(String phone);
}
