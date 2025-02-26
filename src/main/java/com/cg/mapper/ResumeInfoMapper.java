package com.cg.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.ResumeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 简历表 Mapper 接口
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface ResumeInfoMapper extends BaseMapper<ResumeInfo> {

    Page<ResumeInfo> getpage(Page<ResumeInfo> objectPage, Long userId);
}
