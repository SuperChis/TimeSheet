package org.example.timesheet.dto.position;

import lombok.Data;

import java.util.Date;
@Data
public class PositionDTO {

    private Long id;

    private String position;

    private Date created;
}
