package org.example.timesheet.service;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.JwtResponse;
import org.example.timesheet.dto.auth.ResetPasswordRequest;
import org.example.timesheet.dto.auth.SignInRequest;
import org.example.timesheet.dto.auth.SignUpRequest;
import org.example.timesheet.dto.auth.SignUpResponse;
import org.example.timesheet.dto.user.UserRequest;
import org.example.timesheet.dto.user.UserResponse;
import org.example.timesheet.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    SignUpResponse signUp(SignUpRequest request, BindingResult bindingResult);

    JwtResponse signIn(SignInRequest request, BindingResult bindingResult);

    Optional<User> findByUserName(String username);

    UserResponse getListUser(Pageable pageable);

    BaseResponse resetPasswordByAdmin(ResetPasswordRequest request);

    BaseResponse resetPasswordByUser(ResetPasswordRequest request);

    BaseResponse deactiveUser(Long userId);

    UserResponse search(String search);

}
