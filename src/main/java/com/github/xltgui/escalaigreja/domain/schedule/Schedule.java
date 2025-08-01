package com.github.xltgui.escalaigreja.domain.schedule;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "DAY")
    private WeekDay day;

    @Enumerated(EnumType.STRING)
    @Column(name = "MONTH")
    private MonthPt monthPt;

    private LocalDate date;
    private LocalTime time;
}
