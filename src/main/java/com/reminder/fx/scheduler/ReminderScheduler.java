package com.reminder.fx.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.reminder.fx.entity.Reminder;
import com.reminder.fx.services.NotificationService;
import com.reminder.fx.services.ReminderService;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReminderScheduler {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        log.info("Running the method [checkReminders]...");
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Reminder> reminders = reminderService.getPendingReminders();

        reminders.stream()
                .filter(reminder -> reminder.getDateTime().truncatedTo(ChronoUnit.MINUTES).equals(now))
                .forEach(reminder -> {
                    Platform.runLater(
                            () -> notificationService.sendNotification("Reminder Alert", reminder.getReminder()));
                });
    }
}
