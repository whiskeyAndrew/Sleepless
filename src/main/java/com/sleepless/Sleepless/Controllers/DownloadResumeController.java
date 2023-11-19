package com.sleepless.Sleepless.Controllers;

import com.sleepless.Sleepless.Configurations.FilesConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/downloads/resume")
@RequiredArgsConstructor
public class DownloadResumeController {
    private final FilesConfiguration filesConfiguration;
    @GetMapping()
    @ResponseBody
    public ResponseEntity<InputStreamSource> getResumeFile(@RequestParam(name = "type") String fileType) {
        System.out.println("DownloadRequest " + fileType);
        try {

            String fullFilePath = new StringBuilder(filesConfiguration.getResumeDirectoryPath()).append("resume.").append(fileType).toString();
            InputStreamResource file = new InputStreamResource(new FileInputStream(fullFilePath));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "resume." + fileType);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }

    }
}
