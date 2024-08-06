package org.example.timesheet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.enums.EWorkingType;

import java.util.Date;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long hour;

    private String note;

    @Enumerated(EnumType.STRING)
    private EWorkingType type;

    private String isApproved;

    private Date workingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
}
