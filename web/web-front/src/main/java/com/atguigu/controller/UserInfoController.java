package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: UserInfoController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/26 20:54
 * @version: 1.0
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Reference
    private UserInfoService userInfoService;

    /**
     * 会员注册
     * @param registerVo
     * @return
     */
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo,HttpServletRequest request){
        String nickName = registerVo.getNickName();
        String password = registerVo.getPassword();
        String phone = registerVo.getPhone();
        String code = registerVo.getCode();

        if(StringUtils.isEmpty(nickName)||
                StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code))  {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        //验证码
        HttpSession session = request.getSession();
        String currentCode = (String)session.getAttribute("CODE");
        if(!code.equals(currentCode)){
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
        UserInfo userInfo = userInfoService.getByPhone(phone);
        if(userInfo!=null){
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        userInfo=new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setPhone(phone);
        userInfo.setPassword(MD5.encrypt(password));
        userInfo.setStatus(1);
        userInfoService.insert(userInfo);
        return Result.ok();
    }

    @RequestMapping("/sendCode/{phoneNum}")
    public Result sendCode(@PathVariable("phoneNum") String phoneNum, HttpServletRequest request){
        int code = new Random().nextInt(9000)+1000;
        HttpSession session = request.getSession();
        session.setAttribute("CODE",code+"");


        return Result.ok(code);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo,HttpServletRequest request){
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){

            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        UserInfo userInfo = userInfoService.getByPhone(phone);

        if(userInfo==null){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        if(!MD5.encrypt(password).equals(userInfo.getPassword())){
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        if(userInfo.getStatus()==0){
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        request.getSession().setAttribute("USER",userInfo);

        HashMap<String, Object> map = new HashMap<>();
        map.put("phone",userInfo.getPhone());
        map.put("nickName",userInfo.getNickName());
        return Result.ok(map);

    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("USER");

        return  Result.ok();
    }

}
