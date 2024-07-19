package com.Sleepless.event.handler;


import com.Sleepless.event.EventManager;
import com.Sleepless.env.twitchWrapper.service.UserTwitchService;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.philippheuer.events4j.simple.domain.EventSubscriber;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatEventsHandler {
    private final UserTwitchService userTwitchService;
    private final TwitchClient twitchClient;
    private final EventManager eventManager;

    @PostConstruct
    public void init() {
        twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(this);
    }

    @EventSubscriber
    public void printChannelMessage(ChannelMessageEvent event) {
        eventManager.handleEvent(event);
    }


}
