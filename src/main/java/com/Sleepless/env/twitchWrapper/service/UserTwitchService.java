package com.Sleepless.env.twitchWrapper.service;


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

    public User getUserById(String id) throws Exception {
        UserList resultList = twitchClient.getHelix().getUsers(config.getAccessToken(), Collections.singletonList(id), null).execute();
        if (resultList.getUsers().isEmpty()) {
            throw new Exception("User " + id + " not found!");
        }
        return resultList.getUsers().get(0);
    }

    public User getUserByName(String name) throws Exception {
        UserList resultList = twitchClient.getHelix().getUsers(config.getAccessToken(), null, Collections.singletonList(name)).execute();
        if (resultList.getUsers().isEmpty()) {
            throw new Exception("User " + name + " not found!");
        }
        return resultList.getUsers().get(0);
    }
}
