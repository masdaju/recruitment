package com.cg.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cg.service.ChatMessageService;
import com.cg.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-10-26
 */
@RestController
@RequestMapping("/chat")
public class ChatMessageAction {


    @Autowired
    private ChatMessageService chatMessageService;
    @GetMapping
    public SaResult list(@RequestParam String sendUserAccount, @RequestParam String acceptUserAccount)  {
    return SaResult.data(chatMessageService.getchatlist(sendUserAccount, acceptUserAccount));
    }


    @PostMapping(value = "/create")
    public SaResult create(@RequestBody ChatMessage params) {
        System.out.println(params);
        chatMessageService.save(params);
        return SaResult.ok("created successfully");
    }


}
