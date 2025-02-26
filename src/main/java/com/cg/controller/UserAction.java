package com.cg.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cg.entity.view.VUser;
import com.cg.service.VUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.service.UserService;
import com.cg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 
 * @since 2024-10-13
 */
@RestController
@RequestMapping("/sys-user")
public class UserAction {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public SaResult login(@RequestParam String account, @RequestParam String password) {
       return userService.login(account, password);

    }

        @GetMapping(value = "/logout")
        public SaResult logout(@RequestHeader String satoken,@RequestParam Integer uid) {
                return userService.logout(satoken,uid);
        }

        @Autowired
   private VUserService vuserService;
    @GetMapping
    public SaResult list(@RequestParam(required = false) Integer current,
                         @RequestParam(required = false) Integer pageSize,
                         @RequestParam(required = false) String name) {
        LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
        if (name == null) {
            wrapper=null;
        }else {
            wrapper.like(VUser::getName, name);
        }
        Page<VUser> page;
        if (current == null || pageSize == null) {
            page = new Page<>();
        }else {
            page = new Page<>(current, pageSize);
        }
        Page<VUser> aPage = vuserService.page(page,wrapper);
        return  SaResult.data(aPage);
    }

    @GetMapping(value = "/{id}")
    public SaResult getById(@PathVariable("id") Long id) {
        return SaResult.data(userService.getById(id));
    }

    @PostMapping(value = "/updatePassWord")
    public SaResult updatePassWord(@RequestParam String oldPassword
            ,@RequestParam String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Long loggedInUserId = StpUtil.getLoginIdAsLong();
        User one = userService.getById(loggedInUserId);
        if (!passwordEncoder.matches(oldPassword, one.getPassword())) {
            return SaResult.error("旧密码错误");
        }
        one.setPassword(passwordEncoder.encode(newPassword));
        userService.updateById(one);
        return SaResult.ok("修改成功");

    }

    @PostMapping(value = "/create")
    public SaResult create(@RequestBody User params) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        params.setPassword(passwordEncoder.encode(params.getPassword()));
        userService.save(params);
        return SaResult.ok("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public SaResult delete(@PathVariable("id") String id) {
        userService.removeById(id);
        return SaResult.ok("deleted successfully");
    }

    @PostMapping(value = "/update")
    public SaResult update(@RequestBody User params) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        params.setPassword(passwordEncoder.encode(params.getPassword()));
        userService.updateById(params);
        return SaResult.ok("updated successfully");
    }
}
