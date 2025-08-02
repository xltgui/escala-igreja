package com.github.xltgui.escalaigreja.repository;

import com.github.xltgui.escalaigreja.domain.schedule.MonthPt;
import com.github.xltgui.escalaigreja.domain.schedule.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, QuerydslPredicateExecutor<Schedule> {
    List<Schedule> findByMonthPt(MonthPt monthPt);

    boolean existsByMonthPtAndDateAndTime(MonthPt monthPt ,LocalDate date, LocalTime time);
}
