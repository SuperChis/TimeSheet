package org.example.timesheet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userWasAssigned;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
