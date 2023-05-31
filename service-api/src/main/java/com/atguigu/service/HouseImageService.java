package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseImage;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: HouseImageService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 18:49
 * @version: 1.0
 */
public interface HouseImageService extends BaseService<HouseImage> {
    List<HouseImage> findListByHouseId(Long HouseId,Integer type);
}
