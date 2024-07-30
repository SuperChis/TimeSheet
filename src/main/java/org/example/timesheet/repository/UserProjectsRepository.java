package org.example.timesheet.repository;

import org.example.timesheet.entity.UserProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectsRepository extends JpaRepository<UserProjects, Long> {
    @Query(value = "SELECT up " +
            "       FROM user_projects up " +
            "       WHERE up.user.id = :userId " +
            "           AND up.project.id = :projectId " +
            "           AND up.position.position = :position " +
            "           AND up.project.isActive = true ")
    UserProjects findByUserIdAndProjectId(Long userId, Long projectId, String position);
}
