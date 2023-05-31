package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: UserFollowService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/27 16:29
 * @version: 1.0
 */
public interface UserFollowService extends BaseService<UserFollow> {
    Boolean isFollowed(Long userInfoId, Long houseId);
    public PageInfo<UserFollowVo> findListPage(Map<String, Object> filters);

}
