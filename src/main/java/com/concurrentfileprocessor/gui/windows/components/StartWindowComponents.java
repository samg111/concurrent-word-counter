package com.concurrentfileprocessor.gui.windows.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * component factory class for start window UI elements
 * creates and configures all labels and buttons
 */
public class StartWindowComponents {
    // UI component fields for start window
    public Label welcomeLabel;
    public Label instructionLabel;
    public Label descriptionLabel;
    public Button startButton;
    public Button quitButton;
    
    /**
     * constructor
     * @param welcomeLabel main application title label
     * @param instructionLabel instruction text for user
     * @param descriptionLabel description of application functionality
     * @param startButton button to begin application
     * @param quitButton button to exit application
     */
    public StartWindowComponents(Label welcomeLabel, Label instructionLabel, Label descriptionLabel, Button startButton, Button quitButton) {
        this.welcomeLabel = welcomeLabel;
        this.instructionLabel = instructionLabel;
        this.descriptionLabel = descriptionLabel;
        this.startButton = startButton;
        this.quitButton = quitButton;
    }

    /**
         * creates all UI components for the start window
         * @return StartWindowComponents object with all components configured
     */
    public static StartWindowComponents createStartWindowComponents() {
        // create main application title label
        Label welcomeLabel = new Label("Concurrent File Processor");
        welcomeLabel.setFont(Font.font("System", 32));
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setStyle("-fx-text-fill: #1e40af; -fx-font-weight: bold;");

        // create instruction label for user guidance
        Label instructionLabel = new Label("Click Start to continue");
        instructionLabel.setFont(Font.font("System", 24));
        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setStyle("-fx-text-fill: #475569, -fx-font-weight: bold;");

        // create description label explaining application purpose
        Label descriptionLabel = new Label("Process multiple text files concurrently to analyze word frequency, " +
                                            "line counts, and character statistics with multi-threaded performance");
        descriptionLabel.setFont(Font.font("System", 18));
        descriptionLabel.setAlignment(Pos.CENTER);
        descriptionLabel.setStyle("-fx-text-fill: #475569; -fx-text-alignment: center;");
        descriptionLabel.setWrapText(true);

        // create start button to begin application
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("System", 20));
        startButton.setPrefSize(150, 45);
        startButton.setStyle(
            "-fx-background-color: #059669;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );

        Button quitButton = ButtonCreator.createQuitButton();

        return new StartWindowComponents(welcomeLabel, instructionLabel, descriptionLabel, startButton, quitButton);
    }
}
