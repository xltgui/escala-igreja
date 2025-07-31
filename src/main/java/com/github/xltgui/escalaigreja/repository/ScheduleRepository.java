package com.github.xltgui.escalaigreja.repository;

import com.github.xltgui.escalaigreja.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
