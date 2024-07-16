package com.Sleepless.env.user.service;


import com.Sleepless.config.TwitchServerConfig;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.UserList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserTwitchService {
    private final TwitchClient twitchClient;
    private final TwitchServerConfig config;

    public User getUserById(String id){
        UserList resultList =  twitchClient.getHelix().getUsers(config.getAccessToken(), Collections.singletonList(id),null).execute();
        return resultList.getUsers().get(0);
    }
}
