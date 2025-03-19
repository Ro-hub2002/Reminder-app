package com.reminder.fx.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reminder.fx.entity.Reminder;
import com.reminder.fx.interfaces.IReminderService;
import com.reminder.fx.repository.ReminderRepository;

@Service
public class ReminderService implements IReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public List<Reminder> getReminders() {
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders.isEmpty() ? Collections.emptyList() : reminders;
    }

    @Override
    public Reminder saveReminder(String reminder, LocalDateTime date) {
        Reminder newReminder = new Reminder();
        if (reminder == null || reminder.isBlank() || date == null) {
            throw new IllegalArgumentException("Reminder and date cannot be null.");
        }
        newReminder.setReminder(reminder);
        newReminder.setDateTime(date);
        return reminderRepository.save(newReminder);
    }

    @Override
    public void deleteReminder(Reminder reminder) {
        if (!existById(reminder.getId())) {
            throw new IllegalArgumentException("Reminder does not exist or is null.");
        }
        reminderRepository.delete(reminder);
    }

    @Override
    public boolean existById(Long id) {
        return reminderRepository.existsById(id);
    }

    @Override
    public List<Reminder> getPendingReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteAgo = now.minusMinutes(1);
        List<Reminder> listPending = reminderRepository.findPendingReminders(oneMinuteAgo, now);
        return Objects.nonNull(listPending) ? listPending : Collections.emptyList();
    }
}
