package com.cg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.ResumeInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 简历表 服务类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
public interface ResumeInfoService extends IService<ResumeInfo> {

    boolean saveResume(ResumeInfo resumeInfo, MultipartFile file);

    Page<ResumeInfo> getpage(Page<ResumeInfo> objectPage, Long userId);

    boolean updateResume(ResumeInfo resumeInfo, MultipartFile file);
}
