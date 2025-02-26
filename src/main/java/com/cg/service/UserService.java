package com.cg.service;

import cn.dev33.satoken.util.SaResult;
import com.cg.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MIZUGI
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2024-10-10 10:27:35
*/
public interface UserService extends IService<User> {

    SaResult login(String account, String password);


    SaResult logout(String satoken, Integer userId);
}
