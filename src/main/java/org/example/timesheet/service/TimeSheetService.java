package org.example.timesheet.service;

import org.example.timesheet.dto.timesheet.TimeSheetRequest;
import org.example.timesheet.dto.timesheet.TimeSheetResponse;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface TimeSheetService {

    TimeSheetResponse createTimesheetByUser (TimeSheetRequest request);

    TimeSheetResponse getTimesheetByUser(String search, Date date, Pageable pageable);

    TimeSheetResponse getTimesheetByAdmin(String search, Date date, Pageable pageable);

    TimeSheetResponse reviewTimesheetByAdmin(TimeSheetRequest request);

}
