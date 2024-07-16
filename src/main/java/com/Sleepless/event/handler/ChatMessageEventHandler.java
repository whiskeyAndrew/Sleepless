package com.Sleepless.event.handler;

import com.Sleepless.env.chatMessage.service.ChatMessageService;
import com.Sleepless.env.chatMessage.utils.ChatMessageAdapter;
import com.Sleepless.twitchutils.TwitchChatMessageSender;
import com.Sleepless.env.user.service.UserService;
import com.Sleepless.env.user.service.UserTwitchService;
import com.Sleepless.env.user.utils.UserAdapter;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatMessageEventHandler {
    private final UserTwitchService userTwitchservice;
    private final UserService userService;
    private final TwitchChatMessageSender messageSender;
    private final ChatMessageService chatMessageService;

    public void execute(ChannelMessageEvent event) {
        log.info("[" + event.getChannel().getName() + "]["
                + event.getPermissions().toString() + "] "
                + event.getUser().getName() + ": "
                + event.getMessage());

        userService.updateOrAddIfNeededById(UserAdapter.eventUserToEntity(event.getUser()));
        chatMessageService.save(ChatMessageAdapter.twitchChatMessageToEntity(event));
//        if (event.getMessage().startsWith("!test")) {
//            messageSender.sendSimpleMessageToTarget(event.getUser().getName(), "Отвечаю на тестовое сообщение");
//        }
    }
}
