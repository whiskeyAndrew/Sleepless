package com.Sleepless.env.twitchWrapper;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.env.twitchWrapper.controller.response.TwitchChangeChannelResponse;
import com.Sleepless.env.twitchWrapper.service.UserTwitchService;
import com.github.twitch4j.helix.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChangeChatConsumer {
    private final TwitchServerConfig twitchConfig;
    private final UserTwitchService twitchService;

    @KafkaListener(topics = "CHANGE_CHANNEL_TOPIC", groupId = "group_id")
    public void consume(String message) {
        User targetChannel = twitchService.getUserByName(message);
        if (targetChannel == null) {
            log.info("Can't find channel: " + message);
            return;
        }

        try {
            log.info("Trying to change to channel: " + message + " by kafka message");

            if (StringUtils.isBlank(targetChannel.getLogin())) {
                throw new RuntimeException("Target channel is blank");
            }

            twitchConfig.changeChannel(targetChannel.getLogin());
        } catch (Exception e) {
            log.error("Failed to change to channel: " + targetChannel.getLogin(), e);
        }
        log.info("Successfully changed channel: " + targetChannel.getLogin());
    }
}

