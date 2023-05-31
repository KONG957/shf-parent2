package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: HouseService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 9:01
 * @version: 1.0
 */
public interface HouseService extends BaseService<House> {

    void publish(Long id, Integer status);

    PageInfo<HouseVo> findListPage (Integer pageNum , Integer pageSize , HouseQueryVo houseQueryVo);
}
