package org.example.timesheet.dto.position;

import lombok.Data;

import java.util.Date;

@Data
public class PositionRequest {

    private Long id;

    private String position;

    private Date created;
}
