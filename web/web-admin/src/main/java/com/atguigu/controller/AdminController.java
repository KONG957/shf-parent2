package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Role;
import com.atguigu.result.Result;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.util.QiniuUtils;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: AdminController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 17:21
 * @version: 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Reference
    private AdminService adminService;

    @Reference
    private RoleService roleService;

    private final static String LIST_ACTION = "redirect:/admin";

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_UPLOED_SHOW = "admin/upload";
    private final static String PAGE_ASSGIN_SHOW = "admin/assginShow";

    @PreAuthorize("hasAuthority('admin.show')")
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * 进入新增页面
     * @param
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('admin.create')")
    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;

    }


    /**
     * 保存新增
     * @param admin
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('admin.create')")
    @PostMapping("/save")
    public String save(Admin admin) {
        //设置默认头像
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        adminService.insert(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 进入编辑页面
     * @param model
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin.edit')")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     * @param
     * @param admin
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('admin.edit')")
    @PostMapping(value="/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin.delete')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return LIST_ACTION;
    }

    @PreAuthorize("hasAuthority('admin.edit')")
    @RequestMapping("/uploadShow/{id}")
    public String uploadShow(@PathVariable("id") Long id,ModelMap modelMap ){
        modelMap.addAttribute("id",id);
        return PAGE_UPLOED_SHOW;


    }

    @PreAuthorize("hasAuthority('admin.edit')")
    @RequestMapping("/upload/{id}")
    public String upload(@PathVariable("id") Long id, MultipartFile file) throws IOException {
            String newFileName= UUID.randomUUID().toString();
            QiniuUtils.upload2Qiniu(file.getBytes(),newFileName);
            String url="http://rv6zcix7s.hd-bkt.clouddn.com/"+newFileName;
            Admin admin = new Admin();
            admin.setHeadUrl(url);
            admin.setId(id);
            adminService.update(admin);

        return PAGE_SUCCESS;
    }
    @PreAuthorize("hasAuthority('admin.assgin')")
    @GetMapping("/assignShow/{adminId}")
    public String assignShow(@PathVariable Long adminId,ModelMap modelMap){
        Map<String, Object> roleMap = adminService.findRoleByAdminId(adminId);
        modelMap.addAllAttributes(roleMap);
        modelMap.addAttribute("adminId",adminId);
        return PAGE_ASSGIN_SHOW;
    }
    @PreAuthorize("hasAuthority('admin.assgin')")
    @RequestMapping("/assignRole")
    public String assignRole(Long adminId,Long[] roleIds){

        adminService.saveUserRoleRealtionShip(adminId,roleIds);
        return PAGE_SUCCESS;
    }

}
