package com.cg.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 招聘记录表 Mapper 接口
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface RecruitRecordMapper extends BaseMapper<RecruitRecord> {

    Page<RecruitRecord> getpage(Page<RecruitRecord> page, String title, Long compId, Long userId);
}
