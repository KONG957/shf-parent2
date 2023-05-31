package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: HouseBrokerController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 20:26
 * @version: 1.0
 */
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {


    @Reference
    private AdminService adminService;
    @Reference
    private HouseBrokerService houseBrokerService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_EDIT = "houseBroker/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    @RequestMapping("/create")
    public String create(Model model,Long houseId){
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList",adminList);
        model.addAttribute("houseId",houseId);
        return PAGE_CREATE;
    }

    @RequestMapping("/save")
    public String save(HouseBroker houseBroker){
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());

        houseBrokerService.insert(houseBroker);
        return PAGE_SUCCESS;
    }


    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        HouseBroker houseBroker = houseBrokerService.getById(id);
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("houseBroker",houseBroker);
        model.addAttribute("adminList",adminList);
        return PAGE_EDIT;
    }

    @RequestMapping("/update")
    public String update(Long brokerId, Long id){
        Admin admin = adminService.getById(brokerId);
        HouseBroker houseBroker = new HouseBroker();
        houseBroker.setId(id);
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerId(admin.getId());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());

        houseBrokerService.update(houseBroker);
        return PAGE_SUCCESS;
    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId") Long houseId,@PathVariable("id") Long id){
        houseBrokerService.delete(id);
        return LIST_ACTION+houseId;
    }

}
