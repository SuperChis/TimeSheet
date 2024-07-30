package org.example.timesheet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.enums.EPosition;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private Date created;
}
