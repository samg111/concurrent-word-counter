package com.concurrentwordcounter.runners;

import com.concurrentwordcounter.gui.JavaFxApp;

import javafx.application.Application;

public class GuiRunner {
    public static void launch() {
        System.out.println("Running in GUI mode");
        Application.launch(JavaFxApp.class);
    }
}
