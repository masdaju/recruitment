package com.cg.service.impl;

import com.cg.entity.ChatMessage;
import com.cg.mapper.ChatMessageMapper;
import com.cg.service.ChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-10-26
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

    @Override
    public List<ChatMessage> getchatlist(String sendUserAccount, String acceptUserAccount) {
       return baseMapper.getchatList(sendUserAccount,acceptUserAccount);
    }

}
