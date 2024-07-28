package com.Sleepless.env.twitchWrapper.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TwitchChangeChannelResponse {
    private String channel;
    private String error;
}