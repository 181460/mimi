package com.ilit.ssm.service.impl;

import com.ilit.ssm.mapper.AdminMapper;
import com.ilit.ssm.pojo.Admin;
import com.ilit.ssm.pojo.AdminExample;
import com.ilit.ssm.service.AdminService;
import com.ilit.ssm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-05-21:27
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    /**
     *
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public Admin login(String name, String pwd) {
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size() > 0){
            Admin admin = list.get(0);
            //根据传入的用户名和密码进行查询，注意密码是密文的
            String miPwd = MD5Util.getMD5(pwd);
            if(miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
