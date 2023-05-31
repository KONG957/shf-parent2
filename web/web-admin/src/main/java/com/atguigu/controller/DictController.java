package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: DictController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 11:15
 * @version: 1.0
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private final static String  PAGE_INDEX = "dict/index";
    @Reference
    private DictService dictService;


    @GetMapping
    public String index(){


        return PAGE_INDEX;
    }
//查找节点的孩子并判断是否为叶子节点
    @ResponseBody
    @GetMapping("/findZnodes")
    public Result findByParentId(@RequestParam(value = "id", defaultValue = "0") Long id){
        List<Map<String, Object>> znodes = dictService.findZnodes(id);
       return Result.ok(znodes);
    }
//用来返回字典中对应id的子节点(用在前端显示下拉框)
    @GetMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }
//好像目前并未使用
    @GetMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }
}
