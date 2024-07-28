package SleeplessTG.service;

import SleeplessTG.util.ServerEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class SPServersHandler {
    List<ServerEntity> serversList = new ArrayList<>();
}
