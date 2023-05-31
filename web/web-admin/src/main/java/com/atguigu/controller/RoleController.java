package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: RoleController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/15 19:30
 * @version: 1.0
 */
@Controller
@RequestMapping(value="/role")
@SuppressWarnings({"unchecked", "rawtypes"}) //上述注解是jse提供的注解。作用是屏蔽一些无关紧要的警告。使开发者能看到一些他们真正关心的警告。从而提高开发者的效率
public class RoleController  extends BaseController {
    @Reference
    private RoleService roleService;
    @Reference
    private PermissionService permissionService;


    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_SUCCESS= "common/successPage";
    private final static String PAGE_EDIT=  "role/edit";

    private final static String PAGE_ASSGIN_SHOW = "role/assginShow";


    /**
     * 列表
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('role.show')")
    @RequestMapping
    public String index(ModelMap model,HttpServletRequest request)  {

//        List<Role> list = roleService.findAll();
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> roles = roleService.findPage(filters);

        model.addAttribute("page", roles);
        model.addAttribute("filters",filters);
        return PAGE_INDEX;
    }

    @PreAuthorize("hasAuthority('role.create')")
    @GetMapping("/create")
    public String create(Model model){
        return PAGE_CREATE;
    }

    @PreAuthorize("hasAuthority('role.create')")
    @PostMapping ("/save")
    public String save(Role role, HttpServletRequest request){

        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAuthority('role.edit')")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_EDIT;
    }

    @PreAuthorize("hasAuthority('role.edit')")
   @PostMapping("/update")
    public String update( Role role){
        roleService.update(role);
        return PAGE_SUCCESS;
   }
    @PreAuthorize("hasAuthority('role.delete')")
   @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        roleService.delete(id);
       return "redirect:/role";
   }
   @PreAuthorize("hasAuthority('role.assgin')")
    @GetMapping("/assignShow/{roleId}")
    public String assignShow(ModelMap model,@PathVariable Long roleId){

        List<Map<String, Object>> zNodes = permissionService.findPermissionByRoleId(roleId);

        model.addAttribute("roleId",roleId);
        model.addAttribute("zNodes",zNodes);
        return PAGE_ASSGIN_SHOW;
    }

    @PreAuthorize("hasAuthority('role.assgin')")
    @PostMapping("/assignPermission")
    public String assignPermission(Long roleId, Long[] permissionIds){
        permissionService.saveRolePermissionRealtionShip(roleId, permissionIds);
        return PAGE_SUCCESS;
    }





}
