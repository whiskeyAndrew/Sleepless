package com.Sleepless.event.handler;

import com.Sleepless.twitchutils.TwitchChatMessageSender;
import com.Sleepless.user.service.UserService;
import com.Sleepless.user.service.UserTwitchService;
import com.Sleepless.user.utils.UserAdapter;
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

    public void execute(ChannelMessageEvent event) {
        userService.updateOrAddIfNeededById(UserAdapter.eventUserToEntity(event.getUser()));
        log.info("[" + event.getChannel().getName() + "]["
                + event.getPermissions().toString() + "] "
                + event.getUser().getName() + ": "
                + event.getMessage());
//        if (event.getMessage().startsWith("!test")) {
//            messageSender.sendSimpleMessageToTarget(event.getUser().getName(), "Отвечаю на тестовое сообщение");
//        }
    }
}
