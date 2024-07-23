package org.example.timesheet.dto.customer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerRequest {
    private Long id;
    private String name;
    private String address;
}
