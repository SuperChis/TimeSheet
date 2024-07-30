package org.example.timesheet.service;

import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.dto.project.ProjectResponse;

import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ProjectResponse createProjectByAdmin(ProjectRequest request);

    ProjectResponse getProjectListByAdmin(Pageable pageable);

    ProjectResponse editProjectInforByAdmin(ProjectRequest request);

    ProjectResponse deactiveProjecByAdmin(Long id);

    ProjectResponse addMemberToProject(ProjectRequest request);
}
