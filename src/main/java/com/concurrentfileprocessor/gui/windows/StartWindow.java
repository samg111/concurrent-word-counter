package com.concurrentfileprocessor.gui.windows;

import com.concurrentfileprocessor.gui.windows.components.StartWindowComponents;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartWindow {
    private final Controller controller;
    private final Stage stage;

    public StartWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void show() {
        VBox startLayout = new VBox(25);
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setPrefSize(600, 450);
        startLayout.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-padding: 30px;"
        );

        StartWindowComponents components = StartWindowComponents.createStartWindowComponents();
        components.startButton.setOnAction(e -> controller.showMainWindow(stage));
        
        startLayout.getChildren().addAll(
            components.welcomeLabel,
            components.instructionLabel,
            components.descriptionLabel,
            components.startButton,
            components.quitButton
        );

        Scene startScene = new Scene(startLayout, 600, 450);
        stage.setScene(startScene);
        stage.setTitle("Concurrent File Processor");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
