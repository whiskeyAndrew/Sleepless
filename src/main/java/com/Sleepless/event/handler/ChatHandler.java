package com.Sleepless.event.handler;

import com.github.philippheuer.events4j.api.domain.IEvent;
import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.UserTimeoutEvent;
import com.github.twitch4j.eventsub.events.ChannelPointsCustomRewardEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
@Slf4j
public class ChatHandler {

    // the type of the 1st argument is relevant, you can pick any method name you want
    @EventSubscriber
    public void printChannelMessage(ChannelMessageEvent event) {
        System.out.println("[" + event.getChannel().getName() + "]["+event.getPermissions().toString()+"] " + event.getUser().getName() + ": " + event.getMessage());
    }

    @EventSubscriber
    public void printRedemption(ChannelPointsCustomRewardEvent event){
        log.info("Hello world" + event.getClass());
    }
}
