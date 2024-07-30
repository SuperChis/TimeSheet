package org.example.timesheet.controller;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.position.PositionRequest;
import org.example.timesheet.dto.position.PositionResponse;
import org.example.timesheet.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/position")
public class PostitionController {
    @Autowired
    private PositionService service;

    @PostMapping("/admin/create")
    public BaseResponse createPosition(@RequestBody PositionRequest request){
        return service.createPosition(request);
    }

    @GetMapping("/admin")
    public PositionResponse getAllPositionByAdmin(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size){
        return service.getPositions(PageRequest.of(page, size));
    }

    @PutMapping("/admin/update")
    public PositionResponse updatePositionByAdmin(@RequestBody PositionRequest request){
        return service.updatePosition(request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public PositionResponse deletePositionByAdmin(@PathVariable("id") Long id){
        return service.deletePosition(id);
    }
}
