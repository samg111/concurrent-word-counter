package com.concurrentfileprocessor.gui;

import com.concurrentfileprocessor.gui.windows.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller(primaryStage);
        controller.showStartWindow(primaryStage);
    }
}
