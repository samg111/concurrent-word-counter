package com.concurrentwordcounter.gui;

import com.concurrentwordcounter.gui.components.PaneCreator;

import javafx.application.Application;
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
        VBox startLayout = new VBox(20);
        startLayout.setAlignment(Pos.CENTER);
        Label startLabel = new Label("Welcome to the Concurrent Word Counter\nClick Start to continue.");
        Button startButton = new Button("Start");
        startLabel.setAlignment(Pos.CENTER);
        startLabel.setFont(new Font("Arial", 28));
        startButton.setFont(new Font("Arial", 24));
        startLayout.getChildren().addAll(startLabel, startButton);
        Scene startScene = new Scene(startLayout, 600, 400);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Start Window");
        primaryStage.show();

        startButton.setOnAction(e -> {
            showMainWindow();
            primaryStage.close();
        });
    }

    private void showMainWindow() {
        Stage mainStage = new Stage();
        Label welcomeLabel = PaneCreator.createWelcomeLabel("Concurrent Word Counter");
        VBox leftPane = PaneCreator.createLeftPane(mainStage);
        VBox rightPane = PaneCreator.createRightPane(mainStage);
        VBox bottomPane = PaneCreator.createBottomPane();
        HBox centerPane = PaneCreator.createCenterPane(leftPane, rightPane);
        BorderPane root = PaneCreator.createRoot(welcomeLabel, centerPane, bottomPane);
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
