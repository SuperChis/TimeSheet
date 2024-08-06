package org.example.timesheet.repository;

import org.example.timesheet.entity.TimeSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

    @Query(value = "SELECT t " +
            "       FROM TimeSheet t " +
            "       WHERE t.workingDay = :date" +
            "           AND (:search IS NULL OR t.isApproved like %:search% )" +
            "           AND t.userId = :userId"
    )
    Page<TimeSheet> findByDateAndUserId(@Param("search") String search,
                                        @Param("date") Date date,
                                        @Param("userId") Long userId,
                                        Pageable pageable);

    @Query(value = "SELECT t " +
            "       FROM TimeSheet t " +
            "       WHERE t.workingDay = :date" +
            "           AND (:search IS NULL OR t.isApproved like %:search% )"
    )
    Page<TimeSheet> findByDate(@Param("search") String search,
                                        @Param("date") Date date,
                                        Pageable pageable);
}
