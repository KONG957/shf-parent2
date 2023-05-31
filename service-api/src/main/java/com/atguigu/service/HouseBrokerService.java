package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: HouseBrokerService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 19:57
 * @version: 1.0
 */
public interface HouseBrokerService extends BaseService<HouseBroker> {

    List<HouseBroker> findListByHouseId(Long houseId);
}
