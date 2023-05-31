package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: CommunityDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 19:33
 * @version: 1.0
 */
public interface CommunityDao extends BaseDao<Community> {

    List<Community> findAll();

}
