package com.Sleepless.repositories.jpa;

import com.Sleepless.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(timeout = 500)
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsById(String id);
    UserEntity findById(String id);
}
