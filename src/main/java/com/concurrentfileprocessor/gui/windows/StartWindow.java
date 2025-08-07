package com.concurrentfileprocessor.gui.windows;

import com.concurrentfileprocessor.gui.windows.components.StartWindowComponents;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartWindow {
    private Controller controller;
    private Stage stage;

    public StartWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void show() {
        VBox startLayout = new VBox(20);
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setPrefSize(600, 400);
        startLayout.setStyle("-fx-background-color: #64748b;");

        StartWindowComponents components = StartWindowComponents.createStartWindowComponents();
        components.startButton.setOnAction(e -> controller.showMainWindow());
        startLayout.getChildren().addAll(
            components.welcomeLabel,
            components.instructionLabel,
            components.startButton,
            components.quitButton
        );

        Scene startScene = new Scene(startLayout, 600, 400);
        startScene.getRoot().setStyle("-fx-background-color: #64748b;");
        stage.setScene(startScene);
        stage.setTitle("Start Window");
        stage.show();
    }
}
