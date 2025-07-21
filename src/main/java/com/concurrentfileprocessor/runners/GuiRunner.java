package com.concurrentfileprocessor.runners;

import com.concurrentfileprocessor.gui.JavaFxApp;

import javafx.application.Application;

public class GuiRunner {
    public static void launch() {
        System.out.println("Running in GUI mode");
        Application.launch(JavaFxApp.class);
    }
}
