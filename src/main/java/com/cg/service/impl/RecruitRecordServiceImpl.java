package com.cg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.cg.entity.RecruitRecord;
import com.cg.mapper.RecruitRecordMapper;
import com.cg.service.RecruitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招聘记录表 服务实现类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@Service
public class RecruitRecordServiceImpl extends ServiceImpl<RecruitRecordMapper, RecruitRecord> implements RecruitRecordService {

    @Override
    public Page<RecruitRecord> getpage(Integer current, Integer pageSize, String title, Long compId, Long userId) {
        Page<RecruitRecord> page;
        if (current == null || pageSize == null) {
            page = new Page<>();
        }else {
            page = new Page<>(current, pageSize);
        }
        return baseMapper.getpage(page, title, compId, userId);
    }
}
