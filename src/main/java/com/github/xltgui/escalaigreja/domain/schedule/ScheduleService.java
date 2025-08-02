package com.github.xltgui.escalaigreja.domain.schedule;

import com.github.xltgui.escalaigreja.api.ScheduleRowDto;
import com.github.xltgui.escalaigreja.api.schedule.ScheduleCreationRequest;
import com.github.xltgui.escalaigreja.api.schedule.ScheduleRequestParams;
import com.github.xltgui.escalaigreja.domain.liturgicalServer.LiturgicalServer;
import com.github.xltgui.escalaigreja.repository.LiturgicalServersRepository;
import com.github.xltgui.escalaigreja.repository.ScheduleAssignmentRepository;
import com.github.xltgui.escalaigreja.repository.ScheduleRepository;
import com.github.xltgui.escalaigreja.repository.dsl.QDSLSchedule;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final LiturgicalServersRepository liturgicalServersRepository;
    private final ScheduleAssignmentRepository scheduleAssignmentRepository;
    private final ScheduleValidator scheduleValidator;
    private final QDSLSchedule qDSLSchedule;

    @Transactional
    public void create(ScheduleCreationRequest request) {
        scheduleValidator.valid(request);

        Schedule schedule = new Schedule();
        schedule.setDate(request.date());
        schedule.setTime(request.time());
        schedule.setDay(WeekDay.toPortuguese(request.date().getDayOfWeek()));
        schedule.setMonthPt(MonthPt.toPortuguese(request.date().getMonth()));
        scheduleRepository.save(schedule);

        request.assignments().forEach(assignment -> {
            LiturgicalServer server =  liturgicalServersRepository.findById(assignment.serverId())
                .orElseThrow(() -> new RuntimeException("Liturgal Server Not Found with this ID"));

            ScheduleAssignment scheduleAssignment = new ScheduleAssignment();
            scheduleAssignment.setServer(server);
            scheduleAssignment.setSchedule(schedule);
            scheduleAssignment.setDuty(assignment.duty());
            scheduleAssignmentRepository.save(scheduleAssignment);
        });
    }

    public List<ScheduleRowDto> list(ScheduleRequestParams params, Pageable pageable) {
        List<ScheduleRowDto> rows = new ArrayList<>();
        Map<String, String> assignmentsRow = new HashMap<>();

        BooleanBuilder predicate = qDSLSchedule.applyFilter(params);
        Page<Schedule> schedules = scheduleRepository.findAll(predicate, pageable);

        schedules.forEach(schedule -> {
            scheduleAssignmentRepository.findBySchedule(schedule).forEach( assignment -> {
                if(assignment != null){
                    assignmentsRow.put(assignment.getServer().getName(), assignment.getDuty().name());
                }
            });

            ScheduleRowDto row = new ScheduleRowDto(
                    schedule.getId(),
                    schedule.getDay(),
                    schedule.getMonthPt().toString(),
                    schedule.getDate(),
                    schedule.getTime(),
                    assignmentsRow
            );
            rows.add(row);
        });
        return rows;
    }

    public ScheduleRowDto findById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new IllegalArgumentException("Schedule not found with this ID"));

        Map<String, String> assignmentsRow = new HashMap<>();

        scheduleAssignmentRepository.findBySchedule(schedule).forEach( assignment -> {
            if(assignment != null){
                assignmentsRow.put(assignment.getServer().getName(), assignment.getDuty().name());
            }
        });

        ScheduleRowDto row = new ScheduleRowDto(
                schedule.getId(),
                schedule.getDay(),
                schedule.getMonthPt().toString(),
                schedule.getDate(),
                schedule.getTime(),
                assignmentsRow);

        return row;
    }

    @Transactional
    public void update(ScheduleCreationRequest request, Long id) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule Not Found with this ID"));

        schedule.setDate(request.date());
        schedule.setTime(request.time());
        schedule.setDay(WeekDay.toPortuguese(request.date().getDayOfWeek()));
        schedule.setMonthPt(MonthPt.toPortuguese(request.date().getMonth()));
        scheduleRepository.save(schedule);

        List<ScheduleAssignment> existingAssignments = scheduleAssignmentRepository.findBySchedule(schedule);
        scheduleAssignmentRepository.deleteAll(existingAssignments);

        if(!request.assignments().isEmpty()) {
            request.assignments().forEach(assignment -> {
                LiturgicalServer server = liturgicalServersRepository.findById(assignment.serverId())
                        .orElseThrow(() -> new RuntimeException("Liturgal Server Not Found with this ID"));

                ScheduleAssignment scheduleAssignment = new ScheduleAssignment();
                scheduleAssignment.setServer(server);
                scheduleAssignment.setSchedule(schedule);
                scheduleAssignment.setDuty(assignment.duty());
                scheduleAssignmentRepository.save(scheduleAssignment);
            });
        }
    }

    public void delete(long scheduleId) {
        if(scheduleRepository.existsById(scheduleId)){
            scheduleRepository.deleteById(scheduleId);
        }else{
            throw new IllegalArgumentException("Schedule Not Found with this ID");
        }
    }
}
