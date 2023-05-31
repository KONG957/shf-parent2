package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: HouseDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 9:04
 * @version: 1.0
 */
public interface HouseDao extends BaseDao<House> {
    Page<HouseVo> findListPage(@Param("vo") HouseQueryVo houseQueryVo);
}
