package com.base.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.bean.ApiMsg;
import com.base.bean.SysUser;
import com.base.service.SysUserService;
import com.base.service.UserDetailsService;
import com.base.utils.ConsoleUtil;
import com.base.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 登录相关
 * @author: 小猴子
 * @date: 2020-01-16 16:14
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     *  登录页
     * @return
     */
    @RequestMapping("/login.do")
    public String loginPage(){
        return "login";
    }


    /**
     *  登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("loginIn.do")
    @ResponseBody
    public ApiMsg loginIn(@RequestParam String username, @RequestParam String password){

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.setEntity(new SysUser(username,password));
        SysUser user = sysUserService.getUserByUserName(queryWrapper);
        if(user == null){
            return new ApiMsg(1,"用户名密码错误",null);
        }
        String token = jwtUtil.getToken(user.getId()+"");
        Claims claims = jwtUtil.parseToken(token);
        ConsoleUtil.consoleGreen(claims.getSubject());
        //System.out.println("\033[31;0m" +username+","+password + "\033[0m");
        //UsernamePasswordAuthenticationFilter
        return new ApiMsg(0,"登录成功",token);
    }


    @PostMapping("/success.do")
    public String success() {
        System.out.println("===========登录成功");
        //SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return "home/index";
    }

    @PostMapping("/failure.do")
    public String failure() {
        System.out.println("===========登录失败");
        //SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("===========登录失败");
        return "login";
    }

    @PostMapping("/login")
    public void login(String username,String password){

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
       /* User user = userDetailsService.loadUserByUsername(username);*/

        System.out.println("当前登录为:"+username+password);
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(){

        return "login";
    }

}
