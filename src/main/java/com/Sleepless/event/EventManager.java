package com.Sleepless.event;

import com.Sleepless.event.handler.ChatMessageEventHandler;
import com.github.twitch4j.chat.events.TwitchEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class EventManager {

    private final ChatMessageEventHandler chatMessageEventHandler;

    private ExecutorService executorService;
    private static final int THREAD_COUNT = 4;

    public EventManager(ChatMessageEventHandler chatMessageEventHandler) {
        this.chatMessageEventHandler = chatMessageEventHandler;
        initFixedThreadPool();
    }

    private void initCachedThreadPool(){
        executorService = Executors.newCachedThreadPool();
    }

    private void initFixedThreadPool(){
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public void handleEvent(TwitchEvent event){
        if(event instanceof ChannelMessageEvent){
            executorService.execute(() -> {
                chatMessageEventHandler.execute((ChannelMessageEvent) event);
            });
        }
    }
}
