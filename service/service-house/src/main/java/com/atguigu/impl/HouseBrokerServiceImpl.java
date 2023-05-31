package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: HouseBrokerServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 19:58
 * @version: 1.0
 */
@Service(interfaceClass = HouseBrokerService.class)
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

    @Autowired
    private HouseBrokerDao houseBrokerDao;
    @Override
    protected BaseDao getEntityDao() {
        return houseBrokerDao;
    }

    @Override
    public List<HouseBroker> findListByHouseId(Long houseId) {
         List<HouseBroker> houseBrokers =  houseBrokerDao.findListByHouseId(houseId);
         return houseBrokers;
    }
}
