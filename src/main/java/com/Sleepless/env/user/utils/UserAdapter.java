package com.Sleepless.env.user.utils;

import com.Sleepless.env.user.entity.UserEntity;
import com.github.twitch4j.common.events.domain.EventUser;
import com.github.twitch4j.helix.domain.User;

public class UserAdapter {

    public static UserEntity twitchUserToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .login(user.getLogin())
                .displayName(user.getDisplayName())
                .type(user.getDisplayName())
                .broadcasterType(user.getBroadcasterType())
                .description(user.getDescription())
                .email(user.getEmail())
                .accountCreationTime(user.getCreatedAt())
                .build();
    }

    public static UserEntity eventUserToEntity(EventUser user) {
        return UserEntity.builder().id(user.getId()).login(user.getName()).build();
    }
}


