package com.concurrentfileprocessor.gui.windows;

import javafx.stage.Stage;

public class Controller {
    private Stage primaryStage;

    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showStartWindow(Stage primaryStage) {
        StartWindow startWindow = new StartWindow(this, primaryStage);
        startWindow.show();
    }

    public void showMainWindow(Stage primaryStage) {
        MainWindow mainWindow = new MainWindow(this, primaryStage);
        mainWindow.show();
    }

    public void showOutputWindow(Stage primaryStage) {
        OutputWindow outputWindow = new OutputWindow(this, primaryStage);
        outputWindow.show();
    }
}
