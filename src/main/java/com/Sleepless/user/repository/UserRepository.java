package com.Sleepless.user.repository;

import com.Sleepless.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userJpaRepo")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsById(String id);
    UserEntity findById(String id);
}
