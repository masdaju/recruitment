package com.cg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招聘表 服务类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface RecruitInfoService extends IService<RecruitInfo> {

    Page<RecruitInfo> getpage(Integer current, Integer pageSize, String title, Long id);
}
