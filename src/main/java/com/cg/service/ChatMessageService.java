package com.cg.service;

import com.cg.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-10-26
 */
public interface ChatMessageService extends IService<ChatMessage> {

    List<ChatMessage> getchatlist(String sendUserAccount, String acceptUserAccount);


}
