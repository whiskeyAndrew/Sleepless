package com.Sleepless.env.twitchWrapper.controller;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.env.twitchWrapper.controller.request.TwitchChangeChannelRequest;
import com.Sleepless.env.twitchWrapper.controller.response.TwitchChangeChannelResponse;
import com.Sleepless.env.twitchWrapper.service.UserTwitchService;
import com.github.twitch4j.helix.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TwitchWrapperController {

    private final TwitchServerConfig twitchConfig;
    private final UserTwitchService twitchService;

    @PostMapping("/twitch/channel/change")
    public ResponseEntity<TwitchChangeChannelResponse> changeChannel(@RequestBody TwitchChangeChannelRequest request) {
        try {
            User targetChannel = twitchService.getUserByName(request.getChannelName());
            if (StringUtils.isBlank(targetChannel.getLogin())) {
                throw new Exception("Target channel is blank");
            }
            twitchConfig.changeChannel(targetChannel.getLogin());
            return new ResponseEntity<>(TwitchChangeChannelResponse.builder().channel(targetChannel.getLogin()).error("").build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(TwitchChangeChannelResponse.builder().channel(request.getChannelName()).error(e.toString()).build(), HttpStatus.NOT_FOUND);
        }
    }
}
