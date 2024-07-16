package com.Sleepless.env.chatMessage.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageEntity {
    @Id
    private String id;
    private String author;
    private String message;
    private String channelName;

    @ElementCollection
    private Set<String> permissions;


}
