package com.cg.entity.ResponsePOJO;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data

public class RUser implements Serializable {
    /**
     * 主键
     */

    private Long id;


    /**
     * 登录账号
     */

    private String account;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 角色id
     */
    private Long roleId;
    private String roleName;
    private String roleValue;

    private List<String> resource;
    private SaTokenInfo saTokenInfo;
}