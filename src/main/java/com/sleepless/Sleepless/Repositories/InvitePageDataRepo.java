package com.sleepless.Sleepless.Repositories;

import com.sleepless.Sleepless.Entities.InvitePageDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface InvitePageDataRepo extends JpaRepository<InvitePageDataEntity, Long> {
    InvitePageDataEntity getInvitePageDataEntitiesByDate(Date date);
}
