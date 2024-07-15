package com.Sleepless.user.service;

import com.Sleepless.config.TwitchServerConfig;
import com.Sleepless.mongo.repository.UserMongoRepository;
import com.Sleepless.user.entity.UserEntity;
//import com.Sleepless.mongo.repository.UserMongoRepository;
import com.Sleepless.user.repository.UserRepository;
import com.Sleepless.user.utils.UserAdapter;
import com.github.twitch4j.TwitchClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userJpaRepo;

    private final UserMongoRepository userMongoRepo;
    private final UserTwitchService userTwitchService;
    private final TwitchClient twitchClient;
    private final TwitchServerConfig config;

    public void updateOrAddIfNeededById(UserEntity user) {
        String id = user.getId();
        if (!userJpaRepo.existsById(id)) {
            log.info("Creating new user: " + user.getLogin());
            UserEntity entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            saveToAllDatasources(entity);
            return;
        }

        UserEntity currentUser = userJpaRepo.findById(id);
        if (!currentUser.getLogin().equals(user.getLogin())) {
            log.info("Updating user, name was: " + currentUser.getDisplayName() + ", new name: " + user.getDisplayName());
            UserEntity entity = UserAdapter.twitchUserToEntity(userTwitchService.getUserById(user.getId()));
            saveToAllDatasources(entity);
        }
    }

    private void saveToAllDatasources(UserEntity entity) {
        userJpaRepo.save(entity);
        userMongoRepo.save(entity);
    }
}
