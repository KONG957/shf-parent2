package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: DictController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 11:38
 * @version: 1.0
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {
    @Reference
    private DictService dictService;
    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result<List> findListByDictCode(@PathVariable("dictCode") String dictCode){
        List<Dict> dictList = dictService.findListByDictCode(dictCode);
        return Result.ok(dictList);
    }

    @RequestMapping("/findListByParentId/{id}")
    public Result findListByParentId(@PathVariable("id") Long id){
        List<Dict> list = dictService.findListByParentId(id);

        return Result.ok(list);
    }
}
