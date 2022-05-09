package com.ilit.ssm.service;

import com.ilit.ssm.pojo.Admin;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-05-21:25
 */
public interface AdminService {
    /**
     *
     * 管理员登陆验证
     *
     */
    Admin login(String name,String pwd);
}
