package org.example.timesheet.service.impl;

import jakarta.transaction.Transactional;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.dto.project.ProjectDTO;
import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.dto.project.ProjectResponse;
import org.example.timesheet.entity.*;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.ProjectMapper;
import org.example.timesheet.repository.*;
import org.example.timesheet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserProjectsRepository userProjectsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public ProjectResponse createProjectByAdmin(ProjectRequest request) {
        Project projectExist = repository.findByNameAndCustomerName(request);
        if (projectExist != null) {
            throw new NotFoundException(false, 404, "Project exists");
        }
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(request.getCustomerId());
        if (customer == null) {
            throw new NotFoundException(false, 404, "Customer not exists");
        }
        Project project = new Project().setActive(true).setName(request.getName()).setCustomer(customer).setCreated(new Date());
        repository.save(project);
        return new ProjectResponse(true, 200, "project was created successfully");
    }

    @Override
    public ProjectResponse getProjectListByAdmin(Pageable pageable) {
        Page<Project> projectPage = repository.findByIsActiveTrue(pageable);
        List<ProjectDTO> dtos = projectPage.getContent().stream().map(ProjectMapper.MAPPER::toDTO).collect(Collectors.toList());

        return new ProjectResponse(true, 200).setProjectDTOList(dtos).setPageDto(PageDto.populatePageDto(projectPage));
    }

    @Override
    @Transactional
    public ProjectResponse editProjectInforByAdmin(ProjectRequest request) {
        Project project = repository.findByIdAndIsActiveTrue(request.getId());
        if (project == null) {
            throw new NotFoundException(false, 404, "Project not exists");
        }

        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findByIdAndIsDeletedFalse(request.getCustomerId());
            if (customer == null) {
                throw new NotFoundException(false, 404, "customer not found");
            }
            project.setCustomer(customer);
        }

        ProjectMapper.MAPPER.updateProjectFromRequest(request, project);

        return new ProjectResponse(true, 200, "update project information successfully");
    }

    @Override
    public ProjectResponse deactiveProjecByAdmin(Long id) {
        Project project = repository.findByIdAndIsActiveTrue(id);
        if (project == null) {
            throw new NotFoundException(false, 404, "Project not exists");
        }
        project.setActive(false);
        repository.save(project);
        return new ProjectResponse(true, 200, "delete successfully");
    }

    @Override
    public ProjectResponse addMemberToProject(ProjectRequest request) {
        Project project = repository.findByIdAndIsActiveTrue(request.getId());
        if (project == null) {
            throw new NotFoundException(false, 404, "Project not found");
        }

        Optional<User> user = Optional.ofNullable(userRepository.findByIdAndIsDeletedFalse(
                request.getMemberId())
                .orElseThrow(() -> new NotFoundException(false, 404, "user not exists")));

        Position position = positionRepository.findByPosition(request.getPosition());
        if (position == null) {
            throw new NotFoundException(false, 404, "position not exists");
        }

        UserProjects userProjectsCheck = userProjectsRepository.findByUserIdAndProjectId(
                request.getMemberId(),
                request.getId(),
                request.getPosition().trim());
        if (userProjectsCheck != null) {
            throw new RequetFailException(false, 400, "user was in project");
        }

        UserProjects userProjects = new UserProjects()
                .setProject(project)
                .setUser(user.get())
                .setPosition(position);

        userProjectsRepository.save(userProjects);

        return new ProjectResponse(true, 200, "add member to project successfully");
    }
}
