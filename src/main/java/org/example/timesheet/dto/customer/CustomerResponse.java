package org.example.timesheet.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerResponse extends BaseResponse {
    private CustomerDTO customerDTO;
    private List<CustomerDTO> customerDTOList;
    private PageDto pageDto;


    public CustomerResponse(boolean success, int code) {
        super(success, code);
    }

    public CustomerResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
