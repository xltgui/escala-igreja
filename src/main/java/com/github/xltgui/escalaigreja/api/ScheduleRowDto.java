package com.github.xltgui.escalaigreja.api;

import com.github.xltgui.escalaigreja.domain.schedule.WeekDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public record ScheduleRowDto(
        Long scheduleId,
        WeekDay day,
        String month,
        LocalDate date,
        LocalTime time,
        Map<String, String> assignments
) {
}
