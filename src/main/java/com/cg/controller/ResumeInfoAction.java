package com.cg.controller;


import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.ResumeInfo;
import com.cg.service.ResumeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 简历表 前端控制器
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/resume")
public class ResumeInfoAction {


    @Autowired
    private ResumeInfoService resumeInfoService;
    @GetMapping
    public SaResult list(@RequestParam(required = false) Integer current
            , @RequestParam(required = false) Integer pageSize
            ,@RequestParam Long userId) {

        Page<ResumeInfo> aPage;
        if (current == null||pageSize== null) {
            aPage = resumeInfoService.getpage(new Page<>(),userId);
        }else {
            aPage = resumeInfoService.getpage(new Page<>(current, pageSize),userId);
        }
        return SaResult.data(aPage);
    }
    @GetMapping(value = "/{id}")
    public SaResult getById(@PathVariable("id") String id) {
        return SaResult.data(resumeInfoService.getById(id));
    }

    @PostMapping(value = "/create")
    public SaResult create(String params, @RequestParam MultipartFile file) {
        ResumeInfo resumeInfo = JSON.parseObject(params, ResumeInfo.class);
        if (resumeInfoService.saveResume(resumeInfo, file)) {
            return SaResult.ok("created successfully");
        }
        return SaResult.error("created failed");
    }

    @PostMapping(value = "/delete/{id}")
    public SaResult delete(@PathVariable("id") String id) {
        resumeInfoService.removeById(id);
        return SaResult.ok("deleted successfully");
    }
    @PostMapping(value = "/update")
    public SaResult update(String params, @RequestParam MultipartFile file) {
        ResumeInfo resumeInfo = JSON.parseObject(params, ResumeInfo.class);
        if (resumeInfoService.updateResume(resumeInfo, file)) {
            return SaResult.ok("updated successfully");
        }
        return SaResult.error("updated failed");
    }
}
