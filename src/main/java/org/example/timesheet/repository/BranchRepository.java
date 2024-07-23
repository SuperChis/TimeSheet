package org.example.timesheet.repository;

import org.example.timesheet.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByNameAndIsDeletedFalse(String name);

    List<Branch> findByIsDeletedFalse();

    Branch findByIdAndIsDeletedFalse(Long id);
}
