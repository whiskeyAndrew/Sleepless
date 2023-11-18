package com.sleepless.Sleepless.Models;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Builder
@Getter
public class InvitePageCounterToHtmlLine {
    List<Date> dates;
    List<Long> values;
}
