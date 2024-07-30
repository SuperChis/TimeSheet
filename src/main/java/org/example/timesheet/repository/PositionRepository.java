package org.example.timesheet.repository;

import org.example.timesheet.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPosition(String name);

    Optional<Position> findById(Long id);
}
