package com.cg.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.RecruitInfo;
import com.cg.service.RecruitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 招聘表 前端控制器
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/recruit")
public class RecruitInfoAction {


    @Autowired
    private RecruitInfoService recruitInfoService;
//通过title和公司id查询

    @GetMapping
    public SaResult list(@RequestParam(required = false) Integer current,
                         @RequestParam(required = false) Integer pageSize,
                         @RequestParam(required = false) String title,
                        @RequestParam(required = false) Long id) {

        Page<RecruitInfo> aPage = recruitInfoService.getpage(current,pageSize,title,id);
        return  SaResult.data(aPage);
    }

    @GetMapping(value = "/{id}")
    public SaResult getById(@PathVariable("id") String id) {
        return SaResult.data(recruitInfoService.getById(id));
    }

    @PostMapping(value = "/create")
    public SaResult create(@RequestBody RecruitInfo params) {
        System.out.println(params);
        recruitInfoService.save(params);
        return SaResult.ok("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public SaResult delete(@PathVariable("id") String id) {
        recruitInfoService.removeById(id);
        return SaResult.ok("deleted successfully");
    }

    @PostMapping(value = "/update")
    public SaResult update(@RequestBody RecruitInfo params) {
        recruitInfoService.updateById(params);
        return SaResult.ok("updated successfully");
    }
}
