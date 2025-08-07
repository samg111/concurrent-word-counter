package com.concurrentfileprocessor.gui.windows;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
    private Controller controller;
    private Stage stage;

    public MainWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void show() {
        Label welcomeLabel = PaneCreator.createWelcomeLabel("Concurrent File Processor");
        VBox leftPane = PaneCreator.createLeftPane(stage);
        VBox rightPane = PaneCreator.createRightPane(stage);
        VBox bottomPane = PaneCreator.createBottomPane();
        HBox centerPane = PaneCreator.createCenterPane(leftPane, rightPane);
        BorderPane root = PaneCreator.createRoot(welcomeLabel, centerPane, bottomPane);
        root.setStyle("-fx-background-color: #64748b;");
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Concurrent File Processor");
        stage.setScene(scene);
        
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setResizable(true);
        
        stage.centerOnScreen();
        stage.show();
        Platform.runLater(() -> stage.toFront());
    }
}

