package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: HouseImageServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 18:50
 * @version: 1.0
 */
@Service(interfaceClass = HouseImageService.class)
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;
    @Override
    protected BaseDao getEntityDao() {
        return houseImageDao;
    }

    @Override
    public List<HouseImage> findListByHouseId(Long HouseId, Integer type) {
        return houseImageDao.findListByHouseId(HouseId,type);
    }
}
