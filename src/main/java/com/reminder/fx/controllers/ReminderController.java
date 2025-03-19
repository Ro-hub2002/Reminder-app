package com.reminder.fx.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.reminder.fx.services.ReminderService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

@Controller
public class ReminderController {

    @FXML
    private TextField reminderTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> hourSpinner, minuteSpinner;

    @FXML
    private ComboBox<String> zoneTime;

    @Autowired
    private ReminderService reminderService;

    @FXML
    public void initialize() {
        // Configurar AM/PM en el ComboBox
        zoneTime.setItems(FXCollections.observableArrayList("AM", "PM"));
        zoneTime.setValue("AM");

        IntegerSpinnerValueFactory hourFactory = new IntegerSpinnerValueFactory(1, 12, 12);
        hourSpinner.setValueFactory(hourFactory);
        hourSpinner.setEditable(true);

        IntegerSpinnerValueFactory minuteFactory = new IntegerSpinnerValueFactory(0, 59, 0);
        minuteSpinner.setValueFactory(minuteFactory);
        minuteSpinner.setEditable(true);

        setupSpinnerEditor(hourSpinner, hourFactory);
        setupSpinnerEditor(minuteSpinner, minuteFactory);
    }

    private void setupSpinnerEditor(Spinner<Integer> spinner, IntegerSpinnerValueFactory factory) {
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (value >= factory.getMin() && value <= factory.getMax()) {
                    factory.setValue(value);
                } else {
                    spinner.getEditor().setText(oldValue);
                }
            } catch (NumberFormatException e) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }

    @FXML
    private void saveReminder() {
        String reminderText = reminderTextField.getText();
        LocalDate date = datePicker.getValue();
        int hour = hourSpinner.getValue();
        int minute = minuteSpinner.getValue();
        String amPm = zoneTime.getValue();

        if (reminderText.isBlank() || date == null || amPm == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a reminder, date, and time.");
            alert.show();
            return;
        }

        if ("PM".equals(amPm) && hour != 12) {
            hour += 12;
        } else if ("AM".equals(amPm) && hour == 12) {
            hour = 0;
        }

        LocalTime time = LocalTime.of(hour, minute);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        reminderService.saveReminder(reminderText, dateTime);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reminder saved successfully!");
        alert.show();
    }
}
