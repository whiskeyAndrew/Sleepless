package com.sleepless.Sleepless.Controllers;

import com.sleepless.Sleepless.Services.InvitePageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class PublicLinkController {

    private final InvitePageDataService idpService;
    @GetMapping("/invite")
    public String redirectFromInvitePage(){
        idpService.incrementEntryCounter();
        return "redirect:/";
    }

}
