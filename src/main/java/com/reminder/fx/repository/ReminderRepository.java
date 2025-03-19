package com.reminder.fx.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reminder.fx.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("SELECT r FROM Reminder r WHERE r.dateTime BETWEEN :oneMinuteAgo AND :currentTime")
    List<Reminder> findPendingReminders(@Param("oneMinuteAgo") LocalDateTime oneMinuteAgo,
            @Param("currentTime") LocalDateTime currentTime);
}
