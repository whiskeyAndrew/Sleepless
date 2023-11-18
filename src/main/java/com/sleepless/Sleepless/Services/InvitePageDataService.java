package com.sleepless.Sleepless.Services;

import com.sleepless.Sleepless.Entities.InvitePageDataEntity;
import com.sleepless.Sleepless.Repositories.InvitePageDataRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvitePageDataService {
    private final InvitePageDataRepo ipdRepo;

    public void incrementEntryCounter(){
        InvitePageDataEntity ipdEntity =  ipdRepo.getInvitePageDataEntitiesByDate(Date.valueOf(LocalDate.now()));
        if(ipdEntity==null){
            ipdEntity = createNewEntityByCurrentDate();
        }
        ipdEntity.incrementCounter();
        ipdRepo.save(ipdEntity);

    }

    public List<InvitePageDataEntity> getAll(){
        return ipdRepo.findAll();
    }
    private InvitePageDataEntity createNewEntityByCurrentDate(){
        return InvitePageDataEntity.builder().counter(0L).date(Date.valueOf(LocalDate.now())).build();
    }
}
