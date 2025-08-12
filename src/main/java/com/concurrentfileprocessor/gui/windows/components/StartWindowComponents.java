package com.concurrentfileprocessor.gui.windows.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class StartWindowComponents {
    public Label welcomeLabel;
    public Label instructionLabel;
    public Label descriptionLabel;
    public Button startButton;
    public Button quitButton;
    
    public StartWindowComponents(Label welcomeLabel, Label instructionLabel, Label descriptionLabel, Button startButton, Button quitButton) {
        this.welcomeLabel = welcomeLabel;
        this.instructionLabel = instructionLabel;
        this.descriptionLabel = descriptionLabel;
        this.startButton = startButton;
        this.quitButton = quitButton;
    }

    public static StartWindowComponents createStartWindowComponents() {
        Label welcomeLabel = new Label("Concurrent File Processor");
        welcomeLabel.setFont(Font.font("System", 28));
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setStyle("-fx-text-fill: #1e40af; -fx-font-weight: bold;");

        Label instructionLabel = new Label("Click Start to continue");
        instructionLabel.setFont(Font.font("System", 24));
        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setStyle("-fx-text-fill: #475569;");

        Label descriptionLabel = new Label("Process multiple text files concurrently to analyze word frequency, " +
                                            "line counts, and character statistics with multi-threaded performance");
        descriptionLabel.setFont(Font.font("System", 18));
        descriptionLabel.setAlignment(Pos.CENTER);
        descriptionLabel.setStyle("-fx-text-fill: #475569; -fx-text-alignment: center;");
        descriptionLabel.setWrapText(true);

        // Simple start button with basic styling
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("System", 20));
        startButton.setPrefSize(150, 45);
        startButton.setStyle(
            "-fx-background-color: #059669;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );

        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        quitButton.setFont(Font.font("System", 16));
        quitButton.setPrefSize(100, 35);
        quitButton.setStyle(
            "-fx-background-color: #d1d5db;" +
            "-fx-text-fill: #374151;" +
            "-fx-border-color: #9ca3af;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 6;"
        );

        return new StartWindowComponents(welcomeLabel, instructionLabel, descriptionLabel, startButton, quitButton);
    }
}
