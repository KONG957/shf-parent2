package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: HouseUserDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 20:13
 * @version: 1.0
 */
public interface HouseUserDao extends BaseDao<HouseUser> {
    List<HouseUser> findHouseUserListByHouseId(Long houseId);
}
