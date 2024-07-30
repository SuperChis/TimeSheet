package org.example.timesheet.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.timesheet.enums.EWorkingType;

@Entity
@Data
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hour;

    private String note;

    private EWorkingType type;

    private boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
}
