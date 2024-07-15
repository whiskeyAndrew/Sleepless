package com.Sleepless.mongo.repository;

import com.Sleepless.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("userMongoRepo")
public interface UserMongoRepository extends MongoRepository<UserEntity, String> {

}
