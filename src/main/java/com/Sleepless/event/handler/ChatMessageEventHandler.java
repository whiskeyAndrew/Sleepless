package com.Sleepless.event.handler;

import com.Sleepless.user.service.UserService;
import com.Sleepless.user.service.UserTwitchService;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageEventHandler {
    private final UserTwitchService userTwitchservice;
    private final UserService userService;

    public void execute(ChannelMessageEvent event) {
        System.out.println("[" + event.getChannel().getName() + "]["
                + event.getPermissions().toString() + "] "
                + event.getUser().getName() + ": "
                + event.getMessage());
    }
}
