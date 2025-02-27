package com.cg.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.entity.User;
import com.cg.entity.ResponsePOJO.RUser;
import com.cg.entity.view.VRole;
import com.cg.entity.view.VUser;
import com.cg.service.UserService;
import com.cg.mapper.UserMapper;
import com.cg.service.VRoleService;
import com.cg.service.VUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
* @author MIZUGI
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2024-10-10 10:27:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private VUserService vUserService;
    @Autowired
    private VRoleService vRoleService;
    @Override
    public SaResult login(String account, String password) {
        LambdaUpdateWrapper<VUser> wrapper = new LambdaUpdateWrapper<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        wrapper.eq(VUser::getAccount,account);
        VUser vUser = vUserService.getOne(wrapper);
        if (ObjectUtils.isEmpty(vUser)) {
            return SaResult.error("账号不存在");
        }else {
            if (encoder.matches(password,vUser.getPassword())) {
                LambdaQueryWrapper<VRole> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(VRole::getRoleId,vUser.getRoleId());
                List<VRole> list = vRoleService.list(wrapper1);
                List<String> resourceList =list.stream().map(VRole::getResValue).toList();
                StpUtil.login(vUser.getId());
                RUser rUser = new RUser();
                BeanUtils.copyProperties(vUser,rUser);
                rUser.setResource(resourceList);
                rUser.setSaTokenInfo(StpUtil.getTokenInfo());
                System.out.println(StpUtil.getRoleList());
                return SaResult.data(rUser);
            }
            return SaResult.error("密码错误");
        }
    }

    @Override
    public SaResult logout(String satoken, Integer userId) {
            StpUtil.logoutByTokenValue(satoken);
            StpUtil.logout(userId);
        return SaResult.ok("退出成功");
    }
}




