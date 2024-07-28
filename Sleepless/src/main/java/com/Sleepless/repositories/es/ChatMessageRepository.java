package com.Sleepless.repositories.es;

import com.Sleepless.env.chatMessage.entity.ChatMessageEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends ElasticsearchRepository<ChatMessageEntity, Long> {
}
