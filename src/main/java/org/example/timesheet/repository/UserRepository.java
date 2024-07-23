package org.example.timesheet.repository;

import org.example.timesheet.dto.user.UserDTO;
import org.example.timesheet.dto.user.UserRequest;
import org.example.timesheet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndIsDeletedFalse(String username);

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    Optional<User> findByEmailAndIsDeletedFalse(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT new org.example.timesheet.dto.user.UserDTO(" +
            "u.id," +
            "u.username," +
            "u.email," +
            "u.userType," +
            "u.userLevel," +
            "u.isDeleted," +
            "u.sex," +
            "u.branch)" +
            "FROM User u " +
            "LEFT JOIN Branch b ON u.branch.id = b.id " +
            "WHERE u.isDeleted = FALSE " +
            "   AND b.isDeleted = FALSE " +
            "   AND ( (:search IS NULL OR u.username like concat('%', :search, '%'))" +
            "       OR (:search IS NULL OR u.email like concat('%', :search, '%') )" +
            "       OR (:search IS NULL OR u.userType like concat('%', :search, '%') )" +
            "       OR (:search IS NULL OR u.branch.name like concat('%', :search, '%') )" +
            "       OR (:search IS NULL OR u.userLevel like concat('%', :search, '%') )" +
            "       )" +
            "")
    List<UserDTO> search(String search);

}
