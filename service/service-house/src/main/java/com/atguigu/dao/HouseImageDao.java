package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: HouseImageDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 18:50
 * @version: 1.0
 */
public interface HouseImageDao extends BaseDao<HouseImage> {
    List<HouseImage> findListByHouseId(@Param("houseId") Long houseId, @Param("type") Integer type);

}
