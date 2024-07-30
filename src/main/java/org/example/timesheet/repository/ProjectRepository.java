package org.example.timesheet.repository;

import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p " +
            "       FROM Project p " +
            "       WHERE p.name = :#{#request.getName()}" +
            "           AND p.customer.id = :#{#request.getCustomerId()}" +
            "           AND p.isActive = true")
    Project findByNameAndCustomerName(@Param("request") ProjectRequest request);

//    @Query(value = "SELECT p" +
//            "       FROM Project p " +
//            "       WHERE p.id = :id " +
//            "           AND p.isActive = true")
    Project findByIdAndIsActiveTrue(Long id);


    Page<Project> findByIsActiveTrue(Pageable pageable);
}
