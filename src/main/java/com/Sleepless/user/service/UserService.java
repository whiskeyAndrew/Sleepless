package com.Sleepless.user.service;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.user.entity.UserEntity;
import com.Sleepless.user.repository.UserRepository;
import com.Sleepless.user.utils.UserAdapter;
import com.github.twitch4j.TwitchClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserTwitchService userTwitchService;
    private final TwitchClient twitchClient;
    private final TwitchServerConfig config;

    public void updateOrAddIfNeededById(UserEntity user){
        String id = user.getId();
        if (!userRepository.existsById(id)) {
            System.out.println("Creating new user");
            UserEntity entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            userRepository.save(entity);
            return;
        }

        UserEntity currentUser = userRepository.findById(id);
        if (!currentUser.getLogin().equals(user.getLogin())) {
            System.out.println("Updating user, name was: " + currentUser.getDisplayName() + ", new name: " + user.getDisplayName()) ;
            UserEntity entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            userRepository.save(entity);
        }
    }
}
