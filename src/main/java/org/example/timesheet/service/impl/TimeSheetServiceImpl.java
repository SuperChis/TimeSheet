package org.example.timesheet.service.impl;

import org.example.timesheet.config.service.UserDetailsImpl;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.dto.timesheet.TimeSheetDTO;
import org.example.timesheet.dto.timesheet.TimeSheetRequest;
import org.example.timesheet.dto.timesheet.TimeSheetResponse;
import org.example.timesheet.entity.Task;
import org.example.timesheet.entity.TimeSheet;
import org.example.timesheet.enums.EWorkingType;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.TimeSheetMapper;
import org.example.timesheet.repository.TaskRepository;
import org.example.timesheet.repository.TimeSheetRepository;
import org.example.timesheet.service.TimeSheetService;
import org.example.timesheet.utils.TimeConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {
    @Autowired
    private TimeSheetRepository repository;

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public TimeSheetResponse createTimesheetByUser(TimeSheetRequest request) {
        if (request.getType().equals(EWorkingType.NORMAL_TIME.getText()) && request.getHour() > 8L) {
            throw new RequetFailException(false, 400, "require: normal working hours <= 8");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Task task = taskRepository.findByIdAndIsDeletedFalse(request.getTaskId());
        if (task == null) {
            throw new NotFoundException(false, 404, "task not found");
        }
        TimeSheet timeSheet = TimeSheetMapper.MAPPER.toModel(request);
        timeSheet.setWorkingDay(TimeConversion.getStartOfDay(request.getWorkingDay()));
        timeSheet.setIsApproved("pending");
        timeSheet.setUserId(userDetails.getUser().getId());
        timeSheet.setTask(task);
        repository.save(timeSheet);
        return new TimeSheetResponse(true, 200, "create timesheet successfully");
    }

    @Override
    public TimeSheetResponse getTimesheetByUser(String search, Date date, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date dateToQuery = TimeConversion.getStartOfDay(date);
        Page<TimeSheet> timeSheetPage = repository.findByDateAndUserId(search,
                dateToQuery,
                userDetails.getUser().getId(),
                pageable);
        List<TimeSheetDTO> timeSheetDTOList = timeSheetPage.stream()
                .map(TimeSheetMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new TimeSheetResponse(true, 200)
                .setTimeSheetDTOList(timeSheetDTOList)
                .setPageDto(PageDto.populatePageDto(timeSheetPage));
    }

    @Override
    public TimeSheetResponse getTimesheetByAdmin(String search, Date date, Pageable pageable) {
        Date dateToQuery = TimeConversion.getStartOfDay(date);
        Page<TimeSheet> timeSheetPage = repository.findByDate(search,
                dateToQuery,
                pageable);
        List<TimeSheetDTO> timeSheetDTOList = timeSheetPage.stream()
                .map(TimeSheetMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new TimeSheetResponse(true, 200)
                .setTimeSheetDTOList(timeSheetDTOList)
                .setPageDto(PageDto.populatePageDto(timeSheetPage));
    }


    @Override
    public TimeSheetResponse reviewTimesheetByAdmin(TimeSheetRequest request) {
        Optional<TimeSheet> optionalTimeSheet = repository.findById(request.getId());
        if (optionalTimeSheet.isEmpty()) {
            throw new NotFoundException(false, 404, "Timesheet not found");
        }
        TimeSheet timeSheet = optionalTimeSheet.get();
        timeSheet.setIsApproved(request.getIsApproved());
        repository.save(timeSheet);
        return new TimeSheetResponse(true, 200, "timesheet is" + request.getIsApproved());
    }
}
