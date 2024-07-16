package com.Sleepless.repositories.mongo;

import com.Sleepless.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userMongoRepo")
@Transactional(timeout = 500)
public interface UserMongoRepository extends MongoRepository<UserEntity, String> {

}
