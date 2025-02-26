package com.cg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.ResumeInfo;
import com.cg.entity.SysFile;
import com.cg.mapper.ResumeInfoMapper;
import com.cg.service.ResumeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@Service
public class ResumeInfoServiceImpl extends ServiceImpl<ResumeInfoMapper, ResumeInfo> implements ResumeInfoService {
    @Autowired
    SysFileService sysFileService;
    @Override
    public boolean updateResume(ResumeInfo resumeInfo, MultipartFile file) {
        sysFileService.upload(file);
        updateById(resumeInfo);
        return true;
    }

    @Override
    public boolean saveResume(ResumeInfo resumeInfo, MultipartFile file) {
        sysFileService.upload(file);
        LambdaQueryWrapper<SysFile> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysFile::getFileName,file.getOriginalFilename());
        SysFile one = sysFileService.getOne(wrapper);
        resumeInfo.setFileId(one.getId());
        save(resumeInfo);
        return true;
    }

    @Override
    public Page<ResumeInfo> getpage(Page<ResumeInfo> objectPage, Long userId) {
        return baseMapper.getpage(objectPage,userId);
    }
}
