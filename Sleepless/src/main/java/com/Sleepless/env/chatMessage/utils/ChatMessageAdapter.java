package com.Sleepless.env.chatMessage.utils;

import com.Sleepless.env.chatMessage.entity.ChatMessageEntity;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.util.stream.Collectors;

public class ChatMessageAdapter {

    public static ChatMessageEntity twitchChatMessageToEntity(ChannelMessageEvent channelMessageEvent) {
        return ChatMessageEntity.builder()
                .message(channelMessageEvent.getMessage())
                .author(channelMessageEvent.getUser().getName())
                .permissions(channelMessageEvent.getPermissions().stream().map(Enum::name).collect(Collectors.toSet()))
                .channelName(channelMessageEvent.getChannel().getName())
                .build();
    }
}
