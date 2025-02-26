package com.cg.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 招聘表 Mapper 接口
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface RecruitInfoMapper extends BaseMapper<RecruitInfo> {

    Page<RecruitInfo> getpage(Page<RecruitInfo> page, String title, Long id);
}
