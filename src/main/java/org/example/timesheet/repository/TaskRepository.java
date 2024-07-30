package org.example.timesheet.repository;

import org.example.timesheet.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByIdAndIsDeletedFalse(Long id);
}
