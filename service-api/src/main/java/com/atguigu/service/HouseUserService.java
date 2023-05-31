package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: HouseUserService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 20:12
 * @version: 1.0
 */
public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findHouseUserListByHouseId(Long houseId);
}
