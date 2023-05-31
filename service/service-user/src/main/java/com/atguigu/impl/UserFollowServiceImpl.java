package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.UserFollowDao;


import com.atguigu.entity.UserFollow;
import com.atguigu.service.DictService;

import com.atguigu.service.UserFollowService;
import com.atguigu.util.CastUtil;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: UserFollowServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/27 16:29
 * @version: 1.0
 */

@Service(interfaceClass = UserFollowService.class)
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;

    @Reference
    private DictService dictService;


    @Override
    protected BaseDao getEntityDao() {
        return userFollowDao;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);
        PageHelper.startPage(pageNum,pageSize);
        Page<UserFollowVo> page = userFollowDao.findListPage(filters);
        List<UserFollowVo> result = page.getResult();
        for (UserFollowVo userFollowVo : result) {

            String houseTypeName = dictService.getNameById(userFollowVo.getHouseTypeId());
            String floorName = dictService.getNameById(userFollowVo.getFloorId());
            String directionName = dictService.getNameById(userFollowVo.getDirectionId());
            userFollowVo.setHouseTypeName(houseTypeName);
            userFollowVo.setFloorName(floorName);
            userFollowVo.setDirectionName(directionName);
        }
             return new PageInfo<UserFollowVo>(result,3);
    }

    @Override
    public Boolean isFollowed(Long userInfoId, Long houseId) {
      Integer count = userFollowDao.countFollow(userInfoId,houseId);

      if(count!=null&&count>0){
          return true;
      }
      return false;
    }


}
