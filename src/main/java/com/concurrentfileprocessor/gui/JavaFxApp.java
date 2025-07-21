package com.concurrentfileprocessor.gui;

import com.concurrentfileprocessor.gui.components.ButtonCreator;
import com.concurrentfileprocessor.gui.components.PaneCreator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) {
        showStartWindow(primaryStage);
    }

    private void showStartWindow(Stage primaryStage) {
        VBox startLayout = new VBox(20);
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setPrefSize(600, 400);
        startLayout.setStyle("-fx-background-color: #64748b;");
        StartWindowComponents components = createStartWindowComponents();
        startLayout.getChildren().addAll(
            components.welcomeLabel,
            components.instructionLabel,
            components.startButton,
            components.quitButton
        );
        Scene startScene = new Scene(startLayout, 600, 400);
        startScene.getRoot().setStyle("-fx-background-color: #64748b;");
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Start Window");
        primaryStage.show();

        components.startButton.setOnAction(e -> {
            showMainWindow();
            primaryStage.close();
        });
    }

    private static class StartWindowComponents {
        Label welcomeLabel;
        Label instructionLabel;
        Button startButton;
        Button quitButton;
        StartWindowComponents(Label welcomeLabel, Label instructionLabel, Button startButton, Button quitButton) {
            this.welcomeLabel = welcomeLabel;
            this.instructionLabel = instructionLabel;
            this.startButton = startButton;
            this.quitButton = quitButton;
        }
    }

    private StartWindowComponents createStartWindowComponents() {
        Label welcomeLabel = new Label("Welcome to the Concurrent Word Counter");
        welcomeLabel.setFont(new Font("Arial", 28));
        welcomeLabel.setAlignment(Pos.CENTER);

        Label instructionLabel = new Label("Click Start to continue.");
        instructionLabel.setFont(new Font("Arial", 28));
        instructionLabel.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start");
        startButton.setFont(new Font("Arial", 24));
        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        quitButton.setFont(new Font("Arial", 18));

        return new StartWindowComponents(welcomeLabel, instructionLabel, startButton, quitButton);
    }

    private void showMainWindow() {
        Stage mainStage = new Stage();
        Label welcomeLabel = PaneCreator.createWelcomeLabel("Concurrent Word Counter");
        VBox leftPane = PaneCreator.createLeftPane(mainStage);
        VBox rightPane = PaneCreator.createRightPane(mainStage);
        VBox bottomPane = PaneCreator.createBottomPane();
        HBox centerPane = PaneCreator.createCenterPane(leftPane, rightPane);
        BorderPane root = PaneCreator.createRoot(welcomeLabel, centerPane, bottomPane);
        root.setStyle("-fx-background-color: #64748b;");
        Scene scene = new Scene(root, 1280, 720);
        mainStage.setTitle("Concurrent Word Counter");
        mainStage.setScene(scene);
        
        mainStage.setMinWidth(1280);
        mainStage.setMinHeight(720);
        mainStage.setResizable(true);
        
        mainStage.centerOnScreen();
        mainStage.setAlwaysOnTop(true);
        mainStage.show();
    }
}
