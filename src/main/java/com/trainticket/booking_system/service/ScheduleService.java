package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateScheduleRequest;
import com.trainticket.booking_system.dto.response.ScheduleResponse;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateScheduleRequest request);

    List<ScheduleResponse> getAllSchedules();

    ScheduleResponse getScheduleById(String scheduleId);

    ScheduleResponse updateSchedule(String scheduleId, CreateScheduleRequest request);

    void deleteSchedule(String scheduleId);
}
