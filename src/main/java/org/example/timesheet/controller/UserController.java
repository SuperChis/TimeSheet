package org.example.timesheet.controller;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.auth.ResetPasswordRequest;
import org.example.timesheet.dto.user.UserResponse;
import org.example.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/admin/users")
    public UserResponse getListUserByAdmin(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getListUser(pageable);
    }

    @PutMapping("/admin/reset-pass-word")
    public BaseResponse resetPasswordByAdmin(@RequestBody ResetPasswordRequest request){
        return service.resetPasswordByAdmin(request);
    }

    @PutMapping("/user/reset-pass-word")
    public BaseResponse resetPasswordByUser(@RequestBody ResetPasswordRequest request){
        return service.resetPasswordByUser(request);
    }

    @PostMapping("/admin/deactive-user")
    public BaseResponse deactiveUser(@RequestParam Long userId){
        return service.deactiveUser(userId);
    }

    @GetMapping("/admin/search")
    public UserResponse searchByAdmin(@RequestParam(name = "search") String search){
        return service.search(search);
    }

}
