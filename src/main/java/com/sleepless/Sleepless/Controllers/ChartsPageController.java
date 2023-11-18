package com.sleepless.Sleepless.Controllers;

import com.sleepless.Sleepless.Entities.InvitePageDataEntity;
import com.sleepless.Sleepless.Models.InvitePageCounterToHtmlLine;
import com.sleepless.Sleepless.Services.InvitePageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
@RequiredArgsConstructor
public class ChartsPageController {

    private final InvitePageDataService ipdService;
    @GetMapping
    public String getChartPage(Model model){
        return "charts";
    }

    @GetMapping("/entryline")
    @ResponseBody
    public InvitePageCounterToHtmlLine getLineChartData(){
        List<InvitePageDataEntity> ipdAll = ipdService.getAll();
        List<Date> dates = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        ipdAll.forEach(e->{
            dates.add(e.getDate());
            values.add(e.getCounter());
        });


        return InvitePageCounterToHtmlLine.builder().dates(dates).values(values).build();
    }
}
