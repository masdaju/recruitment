package com.cg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 企业表
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@Data
@Accessors(chain = true)
@TableName("comp_info")
public class CompInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 逻辑删除，0 - 正常 1 - 已删除
     */
    @TableField("status")
    private Byte status;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 企业名称
     */
    @TableField("name")
    private String name;

    /**
     * 企业简介
     */
    @TableField("brief")
    private String brief;
}
