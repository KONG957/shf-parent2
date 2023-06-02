package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: HouseController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 8:35
 * @version: 1.0
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    @Reference
    private HouseService houseService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house";

    private final static String PAGE_INDEX = "house/index";
    private final static String PAGE_SHOW = "house/show";
    private final static String PAGE_CREATE = "house/create";
    private final static String PAGE_EDIT = "house/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @PreAuthorize("hasAuthority('house.show')")
    @RequestMapping
    public String index(Model model, HttpServletRequest request){

        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        Map<String, Object> filters = getFilters(request);
        System.out.println(filters);
        PageInfo<House> page = houseService.findPage(filters);
        model.addAttribute("communityList",communityList);
        model.addAttribute("houseTypeList",houseTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("buildStructureList",buildStructureList);
        model.addAttribute("directionList",directionList);
        model.addAttribute("decorationList",decorationList);
        model.addAttribute("houseUseList",houseUseList);
        model.addAttribute("page",page);
        model.addAttribute("filters",filters);



        return PAGE_INDEX;
    }

    @PreAuthorize("hasAuthority('house.create')")
    @RequestMapping("/create")
    public String create(Model model){

        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        model.addAttribute("communityList",communityList);
        model.addAttribute("houseTypeList",houseTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("buildStructureList",buildStructureList);
        model.addAttribute("directionList",directionList);
        model.addAttribute("decorationList",decorationList);
        model.addAttribute("houseUseList",houseUseList);
        return PAGE_CREATE;
    }
    @PreAuthorize("hasAuthority('house.create')")
    @RequestMapping("/save")
    public String save(House house){
        house.setStatus(0);
        houseService.insert(house);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('house.edit')")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        House house = houseService.getById(id);
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");
        model.addAttribute("house",house);
        model.addAttribute("houseTypeList",houseTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("buildStructureList",buildStructureList);
        model.addAttribute("directionList",directionList);
        model.addAttribute("decorationList",decorationList);
        model.addAttribute("communityList",communityList);
        model.addAttribute("houseUseList",houseUseList);

        return PAGE_EDIT;
    }
    @PreAuthorize("hasAuthority('house.edit')")
    @RequestMapping("/update")
    public String update(House house){
        houseService.update(house);
        return PAGE_SUCCESS;
    }
    @PreAuthorize("hasAuthority('house.delete')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        houseService.delete(id);

        return LIST_ACTION;
    }
    @PreAuthorize("hasAuthority('house.edit')")
    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id") Long id,@PathVariable("status") Integer status){

        houseService.publish(id,status);
        return LIST_ACTION;
    }
    @PreAuthorize("hasAuthority('house.show')")
    @RequestMapping("/{id}")
    public String show(Model model,@PathVariable Long id){
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseImage> houseImage1List = houseImageService.findListByHouseId(id, 1);
        List<HouseImage> houseImage2List = houseImageService.findListByHouseId(id, 2);
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        List<HouseUser> houseUserList = houseUserService.findHouseUserListByHouseId(id);
        model.addAttribute("house",house);
        model.addAttribute("houseImage1List",houseImage1List);
        model.addAttribute("houseImage2List",houseImage2List);
        model.addAttribute("community",community);
        model.addAttribute("houseBrokerList",houseBrokerList);
        model.addAttribute("houseUserList",houseUserList);



        return PAGE_SHOW;
    }


}
