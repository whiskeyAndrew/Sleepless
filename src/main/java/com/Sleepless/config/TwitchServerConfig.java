package com.Sleepless.config;

import com.Sleepless.event.handler.ChatHandler;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@Slf4j
@Setter
@RequiredArgsConstructor
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

    private User targetChannelUser;

    private final ChatHandler chatHandler;

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

        initTargetUser(twitchClient);
        registerEventsManagers(twitchClient);
        return twitchClient;
    }

    private void initTargetUser(TwitchClient twitchClient) {
        targetChannelUser = twitchClient.getHelix().getUsers(accessToken, null, Collections.singletonList(targetChannel)).execute().getUsers().get(0);
    }

    private void registerEventsManagers(TwitchClient twitchClient){
        //Регистрируем ивентхэндлеры
        twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(credential(),targetChannelUser.getId());
        twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(chatHandler);

        //Регистрируем ивенты для пабсаба
        twitchClient.getEventManager().onEvent(RewardRedeemedEvent.class, System.out::println);
    }

    @Bean
    public OAuth2Credential credential() {
        return new OAuth2Credential(identityProvider, accessToken);
    }
}
