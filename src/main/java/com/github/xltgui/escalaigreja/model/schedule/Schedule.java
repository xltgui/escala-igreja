package com.github.xltgui.escalaigreja.model.schedule;

import com.github.xltgui.escalaigreja.model.liturgicalServers.LiturgicalServer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private Month month;

    private LocalDate date;
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "DUTY")
    private LiturgicalServersDuty duty;

    @OneToMany
    private List<LiturgicalServer> servers;
}
