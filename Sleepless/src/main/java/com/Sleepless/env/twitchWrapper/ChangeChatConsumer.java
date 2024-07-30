package com.Sleepless.env.twitchWrapper;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.env.twitchWrapper.controller.response.TwitchChangeChannelResponse;
import com.Sleepless.env.twitchWrapper.service.UserTwitchService;
import com.github.twitch4j.helix.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChangeChatConsumer {
    private final TwitchServerConfig twitchConfig;
    private final UserTwitchService twitchService;

    @KafkaListener(topics = "CHANGE_CHANNEL_TOPIC", groupId = "group_id")
    public void consume(String message) {
        System.out.println ("Trying to change to channel: " + message + " by kafka message");
        User targetChannel = twitchService.getUserByName(message);
        if (targetChannel == null) {
            return;
        }
        if (StringUtils.isBlank(targetChannel.getLogin())) {
            throw new RuntimeException("Target channel is blank");
        }
        twitchConfig.changeChannel(targetChannel.getLogin());
    }
}

