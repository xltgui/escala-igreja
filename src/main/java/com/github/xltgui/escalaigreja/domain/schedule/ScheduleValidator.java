package com.github.xltgui.escalaigreja.domain.schedule;

import com.github.xltgui.escalaigreja.api.schedule.ScheduleCreationRequest;
import com.github.xltgui.escalaigreja.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class ScheduleValidator {
    private final ScheduleRepository scheduleRepository;

    public void valid(ScheduleCreationRequest request) {
        MonthPt monthPt = MonthPt.toPortuguese(request.date().getMonth());

        if(scheduleRepository.existsByMonthPtAndDateAndTime(monthPt, request.date(), request.time())){
            throw new IllegalArgumentException("Schedule already exists in this date and time");
        }
    }
}
