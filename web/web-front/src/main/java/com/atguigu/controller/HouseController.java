package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.result.Result;
import com.atguigu.service.*;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: HouseController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 19:31
 * @version: 1.0
 */
@RestController
@RequestMapping(value="/house")
public class HouseController {
    @Reference
    private HouseService houseService;

    @Reference
    private CommunityService communityService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private UserFollowService userFollowService;

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo ,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize){
        //调用HouseService中前端展示房源数据的方法
        PageInfo<HouseVo> pageInfo = houseService.findListPage(pageNum,pageSize,houseQueryVo);
        return Result.ok(pageInfo);
    }

    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable Long houseId, HttpServletRequest httpServletRequest){
        House house = houseService.getById(houseId);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> brokerList = houseBrokerService.findListByHouseId(houseId);
        List<HouseImage> imageList = houseImageService.findListByHouseId(houseId,1);
        Boolean isFollowd = false;
        UserInfo userInfo = (UserInfo)httpServletRequest.getSession().getAttribute("USER");
        if(userInfo!=null){
            Long userInfoId = userInfo.getId();
            isFollowd= userFollowService.isFollowed(userInfoId,houseId);
        }
        Map map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",brokerList);
        map.put("houseImage1List",imageList);

        //是否关注了该房源
        map.put("isFollow",isFollowd);
        return Result.ok(map);


    }

}
