package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.HouseUserDao;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: HouseUserServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 20:13
 * @version: 1.0
 */
@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;
    @Override
    protected BaseDao getEntityDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findHouseUserListByHouseId(Long houseId) {
        return houseUserDao.findHouseUserListByHouseId(houseId);
    }
}
