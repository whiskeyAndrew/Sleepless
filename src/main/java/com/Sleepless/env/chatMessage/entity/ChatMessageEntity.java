package com.Sleepless.env.chatMessage.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="messages")
public class ChatMessageEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String author;
    @Field(type = FieldType.Text)
    private String message;
    @Field(type = FieldType.Text)
    private String channelName;

    @ElementCollection
    @Field(type = FieldType.Auto)
    private Set<String> permissions;


}
