package com.github.xltgui.escalaigreja.web;

import com.github.xltgui.escalaigreja.api.ScheduleRowDto;
import com.github.xltgui.escalaigreja.api.schedule.ScheduleCreationRequest;
import com.github.xltgui.escalaigreja.api.schedule.ScheduleRequestParams;
import com.github.xltgui.escalaigreja.domain.schedule.ScheduleAssignment;
import com.github.xltgui.escalaigreja.domain.schedule.ScheduleService;
import com.github.xltgui.escalaigreja.repository.ScheduleAssignmentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ScheduleCreationRequest request) {
        scheduleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<ScheduleRowDto>> list(ScheduleRequestParams params, @PageableDefault Pageable pageable) {
        List<ScheduleRowDto> rows = scheduleService.list(params, pageable);
        return ResponseEntity.ok().body(rows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRowDto> findByMonth(@PathVariable("id") Long scheduleId) {
        ScheduleRowDto response = scheduleService.findById(scheduleId);
        return ResponseEntity.ok().body(response);
    }



    @PutMapping("{scheduleId}")
    public ResponseEntity<?> update(@PathVariable("scheduleId") long scheduleId, @Valid @RequestBody ScheduleCreationRequest request) {
        scheduleService.update(request, scheduleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
