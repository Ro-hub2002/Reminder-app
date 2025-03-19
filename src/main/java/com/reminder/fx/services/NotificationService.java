package com.reminder.fx.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.reminder.fx.interfaces.INotification;

@Service
public class NotificationService implements INotification {

    @Override
    public void sendNotification(String title, String message) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("powershell", "-Command",
                        "New-BurntToastNotification -Text '" + title + "', '" + message + "'").start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("osascript", "-e",
                        "display notification \"" + message + "\" with title \"" + title + "\"").start();
            } else if (os.contains("nux") || os.contains("nix")) {
                new ProcessBuilder("notify-send", title, message).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
