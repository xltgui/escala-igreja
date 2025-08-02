package com.github.xltgui.escalaigreja.api.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ScheduleCreationRequest(
    @NotNull(message = "Date is required.")
    LocalDate date,

    @NotNull(message = "Time is required.")
    LocalTime time,
    
    @NotEmpty(message = "At least one server assignment is required.")
    @Valid
    List<ScheduleAssignmentRequest> assignments
) {
}