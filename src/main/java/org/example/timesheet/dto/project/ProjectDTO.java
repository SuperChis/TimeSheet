package org.example.timesheet.dto.project;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.timesheet.entity.Customer;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ProjectDTO {
    private Long id;

    private String name;

    private Long customerId;

    private String customerName;

    private String customerAddress;

    private Date created;

    private Date timeStart;

    private Date timeEnd;

    private boolean isActive;
}
