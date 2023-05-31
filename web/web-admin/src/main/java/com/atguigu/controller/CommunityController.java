package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: CommunityController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 19:12
 * @version: 1.0
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    private final static String LIST_ACTION = "redirect:/community";

    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/successPage";


    @RequestMapping//跳转到首页并且将分页信息page  搜索信息filters 下拉框信息areaList 返回给前端
    public String index(Model model, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);

        if(!filters.containsKey("areaId")) {
            filters.put("areaId", "");
        }
        if(!filters.containsKey("plateId")) {
            filters.put("plateId", "");
        }
        PageInfo<Community> page = communityService.findPage(filters);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("page",page);
        model.addAttribute("filters",filters);
        model.addAttribute("areaList",areaList);
        return PAGE_INDEX;

    }

@RequestMapping("/create")
    public String creat(Model model){

        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return PAGE_CREATE;

 }


 @RequestMapping("/save")
    public String save(Community community){
        communityService.insert(community);


        return PAGE_SUCCESS;
 }


 @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){

     Community community = communityService.getById(id);
     List<Dict> areaList = dictService.findListByDictCode("beijing");
     model.addAttribute("community",community);
     model.addAttribute("areaList",areaList);
     return PAGE_EDIT;
 }

@RequestMapping("/update")
    public String update(Community community){

        communityService.update(community);
        return PAGE_SUCCESS;
}


@RequestMapping("/delete/{id}")
public String delete(@PathVariable Long id){

    communityService.delete(id);
    return LIST_ACTION;
}
}
