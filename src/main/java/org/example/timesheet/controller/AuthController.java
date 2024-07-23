package org.example.timesheet.controller;


import org.example.timesheet.dto.JwtResponse;
import org.example.timesheet.dto.auth.SignInRequest;
import org.example.timesheet.dto.auth.SignUpRequest;
import org.example.timesheet.dto.auth.SignUpResponse;
import org.example.timesheet.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/sign-in")
    public JwtResponse singIn(@RequestBody @Valid SignInRequest request, BindingResult bindingResult){
        return service.signIn(request, bindingResult);
    }

    @PostMapping("/sign-up")
    public SignUpResponse singUp(@RequestBody @Valid SignUpRequest request, BindingResult bindingResult) {
        return service.signUp(request, bindingResult);
    }
}
