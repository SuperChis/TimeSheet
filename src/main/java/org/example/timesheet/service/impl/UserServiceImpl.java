package org.example.timesheet.service.impl;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.config.jwt.JwtUtils;
import org.example.timesheet.config.service.UserDetailsImpl;
import org.example.timesheet.dto.JwtResponse;
import org.example.timesheet.dto.auth.ResetPasswordRequest;
import org.example.timesheet.dto.auth.SignInRequest;
import org.example.timesheet.dto.auth.SignUpRequest;
import org.example.timesheet.dto.auth.SignUpResponse;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.dto.user.UserDTO;
import org.example.timesheet.dto.user.UserRequest;
import org.example.timesheet.dto.user.UserResponse;
import org.example.timesheet.entity.Branch;
import org.example.timesheet.enums.ERole;
import org.example.timesheet.entity.Role;
import org.example.timesheet.entity.User;
import org.example.timesheet.enums.EUserLevel;
import org.example.timesheet.enums.EUserType;
import org.example.timesheet.exception.AuthenticationFailException;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.UserMapper;
import org.example.timesheet.repository.BranchRepository;
import org.example.timesheet.repository.RoleRepository;
import org.example.timesheet.repository.UserRepository;
import org.example.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    public SignUpResponse signUp(SignUpRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SignUpResponse(false, 400, getErrMsg(bindingResult));
        }
        if (repository.findByEmailAndIsDeletedFalse(request.getEmail()).isPresent()) {
            return new SignUpResponse(false, 400, "Account is exists");
        }

        Branch branch = branchRepository.findByNameAndIsDeletedFalse(request.getBranch());
        if (branch == null) {
            throw new NotFoundException(false, 404, "Branch not found!!!");
        }

        User user = new User()
                .setUsername(request.getUsername())
                .setEmail(request.getEmail())
                .setPassword(encoder.encode(request.getPassword()))
                .setUserType(request.getUserType())
                .setUserLevel(request.getUserLevel())
                .setBranch(branch)
                .setSex(request.getSex())
                .setDeleted(false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new AuthenticationFailException(false, 401, "Error: Role is not found"));
        roles.add(userRole);
        user.setRoles(roles);
        repository.save(user);

        return new SignUpResponse(true, 200);
    }

    public JwtResponse signIn(SignInRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequetFailException(false, 400, getErrMsg(bindingResult));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Authentication: " + authentication.getPrincipal());
        System.out.println(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt, roles);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return repository.findByUsernameAndIsDeletedFalse(username);
    }

    @Override
    public UserResponse getListUser(Pageable pageable) {
        Page<User> userPage = repository.findAll(pageable);
        List<UserDTO> dtoList = userPage.getContent()
                .stream()
                .map(UserMapper.Mapper::toDTO)
                .collect(Collectors.toList());
        UserResponse response = new UserResponse(true, 200)
                .setUserDTOList(dtoList)
                .setPageDto(PageDto.populatePageDto(userPage));
        return response;
    }

    @Override
    public BaseResponse resetPasswordByAdmin(ResetPasswordRequest request) {
        Optional<User> userOptional = repository.findByIdAndIsDeletedFalse(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new NotFoundException(false, 404, "User not exists");
        }
        if (request == null || request.getPassword().isEmpty()) {
            throw new RequetFailException(false, 400, "password is not empty");
        }
        User user = userOptional.get();
        user.setPassword(encoder.encode(request.getPassword()));
        repository.save(user);
        return new BaseResponse(true, 200, "change password succesfully");
    }

    @Override
    public BaseResponse resetPasswordByUser(ResetPasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        user.setPassword(encoder.encode(request.getPassword()));
        repository.save(user);
        return new BaseResponse(true, 200, "change password succesfully");
    }

    @Override
    public BaseResponse deactiveUser(Long userId) {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty() || userOptional == null) {
            throw new NotFoundException(false, 404, "user not exists");
        }
        User user = userOptional.get();
        if (user.isDeleted()) {
            user.setDeleted(false);
        } else {
            user.setDeleted(true);
        }
        repository.save(user);
        return new BaseResponse(true, 200, "User id: " + userId + " is active: " + user.isDeleted());
    }

    @Override
    public UserResponse search(String search) {
        List<UserDTO> dto = repository.search(search);
        return new UserResponse(true, 200).setUserDTOList(dto);
    }

    @Override
    public UserResponse updateWorkingTimeByAdmin(Long id, UserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequetFailException(false, 400, "Fields in request must be not null or empty");
        }
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(false, 404, "user not exists");
        }
        user.get().setStartTimeWorking(request.getStartTimeWorking());
        user.get().setEndTimeWorking(request.getEndTimeWorking());
        return new UserResponse(true, 200, "update user succesfully!!!");
    }

    private String getErrMsg(BindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        StringBuilder errorMsg = new StringBuilder();
        for (String key : errorMap.keySet()) {
            errorMsg.append("Field Error: ").append(key).append("; Reason: ").append(errorMap.get(key)).append("\n");
        }
        return errorMsg.toString();
    }
}
