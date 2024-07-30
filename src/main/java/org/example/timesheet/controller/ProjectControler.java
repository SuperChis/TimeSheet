package org.example.timesheet.controller;

import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.dto.project.ProjectResponse;
import org.example.timesheet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectControler {

    @Autowired
    private ProjectService service;

    @PostMapping("/admin/create")
    public ProjectResponse createProjectByAdmin (@RequestBody ProjectRequest request) {
        return service.createProjectByAdmin(request);
    }

    @GetMapping("/admin")
    public ProjectResponse getProjectListByAdmin(Pageable pageable){
        return service.getProjectListByAdmin(pageable);
    }

    @PutMapping("/admin/update")
    public ProjectResponse editProjectInforByAdmin(ProjectRequest request){
        return service.editProjectInforByAdmin(request);
    }

    @DeleteMapping("/admin/remove/{id}")
    public ProjectResponse deactiveProjecByAdmin(@PathVariable("id") Long id){
        return service.deactiveProjecByAdmin(id);
    }

    @PostMapping("/admin/project/add-member")
    public ProjectResponse addMemberToProject(ProjectRequest request) {
        return service.addMemberToProject(request);
    }

}
