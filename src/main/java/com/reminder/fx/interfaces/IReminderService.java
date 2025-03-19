package com.reminder.fx.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.reminder.fx.entity.Reminder;

public interface IReminderService {

    List<Reminder> getReminders();
    Reminder saveReminder(String reminder, LocalDateTime date);
    boolean existById(Long id);
    void deleteReminder(Reminder reminder);
    List<Reminder> getPendingReminders();
}
