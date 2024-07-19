package com.Sleepless.env.user.service;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.env.twitchWrapper.service.UserTwitchService;
import com.Sleepless.repositories.mongo.UserMongoRepository;
import com.Sleepless.env.user.entity.UserEntity;
import com.Sleepless.repositories.jpa.UserRepository;
import com.Sleepless.env.user.utils.UserAdapter;
import com.github.twitch4j.TwitchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userJpaRepo;

    private final UserMongoRepository userMongoRepo;
    private final UserTwitchService userTwitchService;
    private final TwitchClient twitchClient;
    private final TwitchServerConfig config;

    public void updateOrAddIfNeededById(UserEntity user) throws Exception {
        String id = user.getId();
        UserEntity entity;
        if (!userJpaRepo.existsById(id)) {
            log.info("Creating new user: " + user.getLogin());

            entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            entity.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            saveToAllDatasources(entity);
            return;
        }

        UserEntity currentUser = userJpaRepo.findById(id);
        if (!currentUser.getLogin().equals(user.getLogin())) {
            log.info("Updating user, name was: " + currentUser.getDisplayName() + ", new name: " + user.getDisplayName());
            entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            entity.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            saveToAllDatasources(entity);
        }
    }

    private void saveToAllDatasources(UserEntity entity) {
        userJpaRepo.save(entity);
        userMongoRepo.save(entity);
    }
}
