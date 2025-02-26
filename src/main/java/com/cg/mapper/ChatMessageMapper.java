package com.cg.mapper;

import com.cg.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-10-26
 */
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

    List<ChatMessage> getchatList(String sendUserAccount, String acceptUserAccount);
}
