package com.concurrentwordcounter.gui;

import com.concurrentwordcounter.gui.components.PaneCreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label welcomeLabel = PaneCreator.createWelcomeLabel("Welcome to the Concurrent Word Counter");
        VBox leftPane = PaneCreator.createLeftPane(primaryStage);
        VBox rightPane = PaneCreator.createRightPane(primaryStage);
        VBox bottomPane = PaneCreator.createBottomPane();
        HBox centerPane = PaneCreator.createCenterPane(leftPane, rightPane);
        BorderPane root = PaneCreator.createRoot(welcomeLabel, centerPane, bottomPane);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
