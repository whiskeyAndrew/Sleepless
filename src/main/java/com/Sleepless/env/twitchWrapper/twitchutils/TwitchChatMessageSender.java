package com.Sleepless.env.twitchWrapper.twitchutils;

import com.Sleepless.config.TwitchServerConfig;
import com.github.twitch4j.TwitchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TwitchChatMessageSender {
    private final TwitchServerConfig twitchServerConfig;
    private final TwitchClient twitchClient;

    public void sendSimpleMessage(String message) {
        twitchClient.getChat().sendMessage(twitchServerConfig.getTargetChannel(), message);
    }

    public void sendSimpleMessageToTarget(String target, String message) {
        twitchClient.getChat().sendMessage(twitchServerConfig.getTargetChannel(), "@" + target + " " + message);
    }
}
