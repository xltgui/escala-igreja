package com.github.xltgui.escalaigreja.api.schedule;

import com.github.xltgui.escalaigreja.domain.schedule.LiturgicalServersDuty;
import jakarta.validation.constraints.NotNull;

public record ScheduleAssignmentRequest (
        @NotNull(message = "Server ID is required.")
        Long serverId,

        @NotNull(message = "Duty is required.")
        LiturgicalServersDuty duty
) {

}
