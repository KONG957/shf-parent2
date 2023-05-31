package com.atguigu.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.config
 * @className: UserDetailsServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/30 20:39
 * @version: 1.0
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username----->"+username);
        Admin admin = adminService.getByUsername(username);
        if(admin==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<String> codeList = permissionService.findCodeListByAdminId(admin.getId());
        Collection<GrantedAuthority> authorities =new ArrayList<>();
        for (String code : codeList) {
            if(StringUtils.isEmpty(code)){
                continue;
            }
            SimpleGrantedAuthority authority  = new SimpleGrantedAuthority(code);
            authorities.add(authority);
        }
        String password = admin.getPassword();
        return new User(username,password, authorities);
    }
}
