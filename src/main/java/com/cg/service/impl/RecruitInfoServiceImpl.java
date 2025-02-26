package com.cg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.entity.RecruitInfo;
import com.cg.mapper.RecruitInfoMapper;
import com.cg.service.RecruitInfoService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招聘表 服务实现类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@Service
@Log4j2
public class RecruitInfoServiceImpl extends ServiceImpl<RecruitInfoMapper, RecruitInfo> implements RecruitInfoService {

    @Value("${spring.datasource.url}")
    private String databaseUrl;


    @Override
    public Page<RecruitInfo> getpage(Integer current, Integer pageSize, String title, Long id) {
        Page<RecruitInfo> page;
        if (current == null || pageSize == null) {
            page = new Page<>();
        }else {
            page = new Page<>(current, pageSize);
        }
        return baseMapper.getpage(page, title, id);
    }
    @Scheduled(cron ="*/3 * * * * ?")
    //每3秒执行一次将过期的招聘信息状态修改为0
    void refresh() {
        log.info(databaseUrl);
        list().forEach(item->{
            if (item.getEndTime().getTime() < System.currentTimeMillis() && item.getStatus() == 1) {
                item.setStatus((byte) 0);
                log.info(updateById(item));
            }

        });

    }

}
