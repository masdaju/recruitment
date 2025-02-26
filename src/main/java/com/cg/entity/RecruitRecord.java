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
 * 招聘记录表
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@Data
@Accessors(chain = true)
@TableName("recruit_record")
public class RecruitRecord implements Serializable {

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
     * 招聘标题
     */
    @TableField("title")
    private String title;

    /**
     * 招聘简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 招聘id
     */
    @TableField("info_id")
    private Long infoId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 简历预览地址
     */
    @TableField("file_url")
    private String fileUrl;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String  companyName;
    @TableField(exist = false)
    private String  account;
}
