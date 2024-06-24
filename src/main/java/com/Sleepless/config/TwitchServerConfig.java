package com.Sleepless.config;

import com.Sleepless.event.handler.ChatHandler;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.reactor.ReactorEventHandler;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Setter
public class TwitchServerConfig {
    String identityProvider = "twitch";

    @Value("${twitch.token.access}")
    private String accessToken;
    @Value("${twitch.token.refresh}")
    private String refreshToken;
    @Value("${twitch.token.client-id}")
    private String clientId;
    @Value("${twitch.target-channel}")
    private String targetChannel;

    @Bean
    public TwitchClient twitchClient() {
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withEnableHelix(true)
                .withEnablePubSub(true)
                .withDefaultEventHandler(SimpleEventHandler.class)
                .withChatAccount(credential())
                .build();
        twitchClient.getChat().joinChannel(targetChannel);
        // register your handler class
        ChatHandler myEventHandler = new ChatHandler();
        twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(myEventHandler);
        return twitchClient;
    }

    @Bean
    public OAuth2Credential credential() {
        return new OAuth2Credential(identityProvider, accessToken);
    }
}
