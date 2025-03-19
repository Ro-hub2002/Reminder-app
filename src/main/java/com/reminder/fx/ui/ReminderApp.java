package com.reminder.fx.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.reminder.fx.ReminderApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReminderApp extends Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(ReminderApplication.class); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main-view.fxml"));
        loader.setControllerFactory(springContext::getBean); // 

        Parent root = loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/styles.css").toExternalForm());

        primaryStage.setTitle("Reminder App");
        primaryStage.setScene(scene);
        primaryStage.setWidth(430); 
        primaryStage.setHeight(250); 
        primaryStage.setResizable(false); 
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
