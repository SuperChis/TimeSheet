package org.example.timesheet.controller;

import org.example.timesheet.dto.timesheet.TimeSheetRequest;
import org.example.timesheet.dto.timesheet.TimeSheetResponse;
import org.example.timesheet.service.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {

    @Autowired
    TimeSheetService service;

    @PostMapping("/user/create")
    public TimeSheetResponse createProjectByUser (@RequestBody TimeSheetRequest request) {
        return service.createTimesheetByUser(request);
    }

    @PostMapping("/admin/review")
    public TimeSheetResponse reviewTimeSheetByAdmin (@RequestBody TimeSheetRequest request) {
        return service.reviewTimesheetByAdmin(request);
    }

    @GetMapping("/admin/list")
    public TimeSheetResponse getTimeSheetByAdmin(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int size,
                                                 @RequestParam(name = "date") Date date,
                                                 @RequestParam(name = "search", required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getTimesheetByAdmin(search, date, pageable);
    }

    @GetMapping("/user/list")
    public TimeSheetResponse getTimeSheetByUser(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int size,
                                                 @RequestParam(name = "date")
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date,
                                                 @RequestParam(name = "search", required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getTimesheetByUser(search, date, pageable);
    }
}
