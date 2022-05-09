package com.ilit.ssm.controller;

import com.ilit.ssm.pojo.Admin;
import com.ilit.ssm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-8:02
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {


    //实现登陆判断，并进行相应的跳转

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){

        Admin admin = adminService.login(name, pwd);

        if(admin != null){
            request.setAttribute("admin",admin);
            return "main";
        }else{
            //登陆失败
            request.setAttribute("errmsg","用户名或者密码不正确");
            return "login";
        }

    }

}
