package org.example.timesheet.repository;

import org.example.timesheet.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNameAndIsDeletedFalse(String name);

    Page<Customer> findByIsDeletedFalse(Pageable pageable);

    Customer findByIdAndIsDeletedFalse(Long id);
}
