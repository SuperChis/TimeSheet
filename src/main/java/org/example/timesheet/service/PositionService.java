package org.example.timesheet.service;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.position.PositionRequest;
import org.example.timesheet.dto.position.PositionResponse;
import org.springframework.data.domain.Pageable;

public interface PositionService {

    BaseResponse createPosition(PositionRequest request);

    PositionResponse getPositions(Pageable pageable);

    PositionResponse updatePosition(PositionRequest request);

    PositionResponse deletePosition(Long id);
}
