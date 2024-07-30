package org.example.timesheet.dto.project;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectRequest {
    private Long id;
    private String name;
    private Long customerId;
    private List<Long> memberIds;
    private Long memberId;
    private String position;
    private Date created;
    private Date timeStart;
    private Date timeEnd;
}
