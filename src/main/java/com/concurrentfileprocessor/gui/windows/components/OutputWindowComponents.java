package com.concurrentfileprocessor.gui.windows.components;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * component factory class for output window UI elements
 * creates and configures all labels and buttons for displaying processing results
 */
public class OutputWindowComponents {
    // UI component fields for output window
    public Label numOfFilesLabel;
    public Label characterCountLabel;
    public Label lineCountLabel;
    public Label wordCountLabel;
    public Button quitButton;
    public Button backMainButton;
    public Button restartButton;

    /**
     * constructor
     * @param numOfFilesLabel label showing number of files processed
     * @param characterCountLabel label showing total characters counted
     * @param lineCountLabel label showing total lines counted
     * @param individualWordCountLabel label showing total words counted
     * @param quitButton button to exit application
     * @param backMainButton button to return to main window
     * @param restartButton button to restart application to start window
     */
    public OutputWindowComponents(Label numOfFilesLabel, Label characterCountLabel, Label lineCountLabel, Label individualWordCountLabel, Button quitButton, 
                                  Button backMainButton, Button restartButton) {
        this.numOfFilesLabel = numOfFilesLabel;
        this.characterCountLabel = characterCountLabel;
        this.lineCountLabel = lineCountLabel;
        this.wordCountLabel = individualWordCountLabel;
        this.quitButton = quitButton;
        this.backMainButton = backMainButton;
        this.restartButton = restartButton;
    }

    /**
     * creates all label components for the output window
     * @return OutputWindowComponents object with all labels configured
     */
    public static OutputWindowComponents createOutputWindowLabels(){
        Label numOfFilesLabel = new Label("Number of files: " + fileStats.numberOfFiles);
        numOfFilesLabel.setFont(Font.font("System", 24));
        numOfFilesLabel.setAlignment(Pos.CENTER);
        numOfFilesLabel.setStyle("-fx-text-fill: #1e40af; -fx-font-weight: bold;");

        Label characterCountLabel = new Label("Total character count: " + fileStats.characterCount);
        characterCountLabel.setFont(Font.font("System", 20));
        characterCountLabel.setAlignment(Pos.CENTER);
        characterCountLabel.setStyle("-fx-text-fill: #374151;");

        Label lineCountLabel = new Label("Total line count: " + fileStats.lineCount);
        lineCountLabel.setFont(Font.font("System", 20));
        lineCountLabel.setAlignment(Pos.CENTER);
        lineCountLabel.setStyle("-fx-text-fill: #374151;");

        // create label showing total number of words counted
        Label wordCountLabel = new Label("Total word count: " + fileStats.wordCount.size());
        wordCountLabel.setFont(Font.font("System", 20));
        wordCountLabel.setAlignment(Pos.CENTER);
        wordCountLabel.setStyle("-fx-text-fill: #374151;");

        return new OutputWindowComponents(numOfFilesLabel, characterCountLabel, lineCountLabel, wordCountLabel, null, null, null);
    }

    /**
     * creates all button components for the output window
     * @param components existing components object to update
     * @return updated components with all buttons configured
     */
    public static OutputWindowComponents createOutputWindowButtons(OutputWindowComponents components){
        Button quitButton = ButtonCreator.createQuitButton();

        // create button to return to main window
        Button backMainButton = ButtonCreator.createButton("Back to Main", (event) -> {
            // event creation in OutputWindow.java
        });
        backMainButton.setFont(Font.font("System", 18));
        backMainButton.setPrefSize(200, 45);
        backMainButton.setStyle(
            "-fx-background-color: #059669;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );

        // create restart button to reset application to start window
        Button restartButton = ButtonCreator.createRestartButton();

        // assign all buttons to components
        components.quitButton = quitButton;
        components.backMainButton = backMainButton;
        components.restartButton = restartButton;

        return components;
    }
}
