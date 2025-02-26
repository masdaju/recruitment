package com.cg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.cg.entity.RecruitRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招聘记录表 服务类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface RecruitRecordService extends IService<RecruitRecord> {

    Page<RecruitRecord> getpage(Integer current, Integer pageSize, String title, Long compId, Long userId);
}
