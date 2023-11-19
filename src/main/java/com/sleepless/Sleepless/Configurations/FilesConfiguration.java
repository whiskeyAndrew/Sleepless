package com.sleepless.Sleepless.Configurations;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Getter
public class FilesConfiguration {
    private StringBuilder resumeDirectoryPath;
    @PostConstruct
    public void init(){
        resumeDirectoryPath = new StringBuilder(new File("").getAbsolutePath()).append("/resumes/");
    }
}
