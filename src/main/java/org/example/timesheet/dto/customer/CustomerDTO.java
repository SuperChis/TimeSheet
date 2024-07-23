package org.example.timesheet.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;

    private String name;

    private String address;
}
