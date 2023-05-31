package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;



/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: UserFollowController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/27 16:22
 * @version: 1.0
 */
@RestController
@RequestMapping("/userFollow")
public class UserFollowController extends BaseController {

@Reference
private UserFollowService userFollowService;

    @RequestMapping("/auth/follow/{id}")
    public Result follow(@PathVariable("id") Long id, HttpServletRequest request){

        UserInfo user = (UserInfo) request.getSession().getAttribute("USER");
        UserFollow userFollow = new UserFollow();
        userFollow.setHouseId(id);
        userFollow.setUserId(user.getId());
        userFollowService.insert(userFollow);
        return Result.ok();
    }
    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result findListPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize,HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userInfo.getId());
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        PageInfo page = userFollowService.findListPage(map);
        return Result.ok(page);
    }

    @RequestMapping("/auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable Long id){
        userFollowService.delete(id);
        return Result.ok();
    }


}
