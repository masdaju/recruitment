package com.cg.controller;


import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.cg.entity.RecruitRecord;
import com.cg.entity.SysFile;
import com.cg.service.RecruitRecordService;
import com.cg.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 招聘记录表 前端控制器
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@RestController

@RequestMapping("/recruit-record")
public class RecruitRecordAction {

    @Autowired
    private RecruitRecordService recruitRecordService;
    @Autowired
    private SysFileService sysFileService;

    @GetMapping
    public SaResult list(@RequestParam(required = false) Integer current,
                         @RequestParam(required = false) Integer pageSize,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) Long compId,
                         @RequestParam(required = false) Long userId) {
        Page<RecruitRecord> aPage = recruitRecordService.getpage(current,pageSize,title,compId,userId);
        return  SaResult.data(aPage);
    }


    @GetMapping(value = "/{id}")
    public SaResult getById(@PathVariable("id") String id) {
        return SaResult.data(recruitRecordService.getById(id));
    }
    @PostMapping(value = "/create")
    public SaResult create(@RequestBody RecruitRecord params) {
        //暂时用url存放文件id
        //获取文件id得到文件地址
        SysFile one = sysFileService.getById(params.getFileUrl());
        //获取到真正的文件地址放入对象中
        params.setFileUrl(one.getFileUrl());
        //存入数据库
        recruitRecordService.save(params);
        return SaResult.ok("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public SaResult delete(@PathVariable("id") String id) {
        recruitRecordService.removeById(id);
        return SaResult.ok("deleted successfully");
    }

    @PostMapping(value = "/update")
    public SaResult update(@RequestBody RecruitRecord params) {
        recruitRecordService.updateById(params);
        return SaResult.ok("updated successfully");
    }
}