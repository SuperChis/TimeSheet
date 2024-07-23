package org.example.timesheet.repository;

import org.example.timesheet.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    LeaveType findByIdAndIsDeletedFalse(Long id);

}
