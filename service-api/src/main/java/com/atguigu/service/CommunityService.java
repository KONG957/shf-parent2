package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: CommunityService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 19:31
 * @version: 1.0
 */
public interface CommunityService extends BaseService<Community> {
        List<Community> findAll();
}
