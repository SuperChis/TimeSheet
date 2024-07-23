package org.example.timesheet.dto.branch;

import lombok.Data;

import java.util.Date;

@Data
public class BranchDTO {

    private Long id;

    private String name;

    private String address;

    private String morningStartTime;

    private String morningEndTime;

    private String afternoonStartTime;

    private String afternoonEndTime;

    private boolean isDeleted;
}
