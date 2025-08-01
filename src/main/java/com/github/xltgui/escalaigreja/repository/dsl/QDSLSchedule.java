package com.github.xltgui.escalaigreja.repository.dsl;

import com.github.xltgui.escalaigreja.api.schedule.ScheduleRequestParams;
import com.github.xltgui.escalaigreja.domain.schedule.QSchedule;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;

@Component
public class QDSLSchedule {

    private final QSchedule qSchedule = QSchedule.schedule;

    public BooleanBuilder applyFilter(ScheduleRequestParams params) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(params.startDate() != null){
            predicate.and(qSchedule.date.goe(params.startDate()));
        }

        if(params.endDate() != null){
            predicate.and(qSchedule.date.loe(params.endDate()));
        }

        if(params.startTime() != null){
            predicate.and(qSchedule.time.goe(params.startTime()));
        }

        if(params.endTime() != null){
            predicate.and(qSchedule.time.loe(params.endTime()));
        }
        return predicate;
    }
}
