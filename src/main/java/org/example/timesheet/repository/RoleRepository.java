package org.example.timesheet.repository;

import org.example.timesheet.enums.ERole;
import org.example.timesheet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

//    @Query(value = "SELECT r " +
//            "FROM ROLE r " +
//            "WHERE r.name = ?1 ")
//    Role findByStringName(String name);
}
