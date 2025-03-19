package com.reminder.fx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.reminder.fx.ui.ReminderApp;

import javafx.application.Application;

@SpringBootApplication
@EnableScheduling
public class ReminderApplication {
    public static void main(String[] args) {
        Application.launch(ReminderApp.class, args); // Inicia JavaFX
    }
}
