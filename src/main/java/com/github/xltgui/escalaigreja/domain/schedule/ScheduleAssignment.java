package com.github.xltgui.escalaigreja.domain.schedule;

import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "SCHEDULE_ASSIGNMENTS")
public class ScheduleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private LiturgicalServer server;

    @Enumerated(EnumType.STRING)
    @Column(name = "DUTY")
    private LiturgicalServersDuty duty;

}
