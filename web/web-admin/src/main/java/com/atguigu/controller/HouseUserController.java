package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: HouseUserController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/25 9:36
 * @version: 1.0
 */

@Controller
@RequestMapping("/houseUser")
public class HouseUserController extends BaseController {


    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseUser/create";
    private final static String PAGE_EDIT = "houseUser/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping("/create")
    public String create(Long houseId, Model model){
        model.addAttribute("houseId",houseId);

        return PAGE_CREATE;
    }

    @RequestMapping("/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return PAGE_SUCCESS;

    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return PAGE_EDIT;
    }

    @RequestMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return PAGE_SUCCESS;


    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId") Long houseId,@PathVariable("id") Long id){
            houseUserService.delete(id);

            return LIST_ACTION+houseId;

    }
}
