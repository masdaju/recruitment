package com.cg.controller;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.entity.CompInfo;
import com.cg.service.CompInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 企业表 前端控制器
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/comp-info")
public class CompInfoAction {

    @Autowired
    private CompInfoService compInfoService;

    @GetMapping
    public SaResult list(@RequestParam(required = false) Integer current,
                         @RequestParam(required = false) Integer pageSize,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) Long userId) {
        LambdaQueryWrapper<CompInfo> wrapper = new LambdaQueryWrapper<>();
        if (name == null) {
            wrapper = null;
        } else wrapper.like(CompInfo::getName, name).eq(userId != null,CompInfo::getUserId, userId);
        Page<CompInfo> page;
        if (current == null || pageSize == null) {
            page = new Page<>();

        } else page = new Page<>(current, pageSize);
        Page<CompInfo> aPage = compInfoService.page(page,wrapper);
        return  SaResult.data(aPage);
    }

    @GetMapping(value = "/{id}")
    public SaResult getById(@PathVariable("id") String id) {
        return SaResult.data(compInfoService.getById(id));
    }

    @PostMapping(value = "/create")
    public SaResult create(@RequestBody CompInfo params) {
        compInfoService.save(params);
        return SaResult.ok("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public SaResult delete(@PathVariable("id") String id) {
        compInfoService.removeById(id);
        return SaResult.ok("deleted successfully");
    }

    @PostMapping(value = "/update")
    public SaResult update(@RequestBody CompInfo params) {
        compInfoService.updateById(params);
        return SaResult.ok("updated successfully");
    }
}