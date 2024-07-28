package com.Sleepless.env.chatMessage.service;

import com.Sleepless.env.chatMessage.entity.ChatMessageEntity;
import com.Sleepless.repositories.es.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public void save(ChatMessageEntity chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
