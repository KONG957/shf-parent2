package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: UserFollowDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/27 16:30
 * @version: 1.0
 */
public interface UserFollowDao extends BaseDao<UserFollow> {

    Integer countFollow(@Param("userInfoId") Long userInfoId, @Param("houseId") Long houseId);
     Page<UserFollowVo> findListPage(Map<String, Object> filters);


}
