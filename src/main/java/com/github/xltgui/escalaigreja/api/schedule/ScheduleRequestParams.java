package com.github.xltgui.escalaigreja.api.schedule;

import com.github.xltgui.escalaigreja.domain.schedule.MonthPt;
import com.github.xltgui.escalaigreja.domain.schedule.WeekDay;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleRequestParams (
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startTime,
        LocalTime endTime
){
}
