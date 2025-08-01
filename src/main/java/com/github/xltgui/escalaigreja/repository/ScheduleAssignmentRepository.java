package com.github.xltgui.escalaigreja.repository;

import com.github.xltgui.escalaigreja.domain.schedule.Schedule;
import com.github.xltgui.escalaigreja.domain.schedule.ScheduleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ScheduleAssignmentRepository  extends JpaRepository<ScheduleAssignment, Long>, QuerydslPredicateExecutor<ScheduleAssignment> {
    List<ScheduleAssignment> findBySchedule(Schedule schedule);

}
