package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: IndexController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/16 12:37
 * @version: 1.0
 */
@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {

    @Reference
    private PermissionService permissionService;

    @Reference
    private AdminService adminService;

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";


    @RequestMapping("/login")
    public String goLoginPage(){
        return "frame/login";
    }

    @GetMapping("/")
    public String index( Model model){
        Authentication authentication=  SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        String username = user.getUsername();
        Admin admin = adminService.getByUsername(username);
        Long adminId = admin.getId();
        List<Permission> permissionList =  permissionService.findMenuPermissionByAdminId(adminId);
        model.addAttribute("permissionList",permissionList);
        model.addAttribute("admin",admin);
        return PAGE_INDEX;
    }
    @GetMapping("/main")
    public String main(){
        return PAGE_MAIN;
    }



}
