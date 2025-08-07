package com.concurrentfileprocessor.gui.windows.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class StartWindowComponents {
    public Label welcomeLabel;
    public Label instructionLabel;
    public Button startButton;
    public Button quitButton;
    
    public StartWindowComponents(Label welcomeLabel, Label instructionLabel, Button startButton, Button quitButton) {
        this.welcomeLabel = welcomeLabel;
        this.instructionLabel = instructionLabel;
        this.startButton = startButton;
        this.quitButton = quitButton;
    }

    public static StartWindowComponents createStartWindowComponents() {
        Label welcomeLabel = new Label("Welcome to the Concurrent File Processor");
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
    
}
