package com.concurrentfileprocessor.gui.windows.components;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class OutputWindowComponents {
    public Label numOfFilesLabel;
    public Label characterCountLabel;
    public Label lineCountLabel;
    public Label wordCountLabel;
    public Button quitButton;

    public OutputWindowComponents(Label numOfFilesLabel, Label characterCountLabel, Label lineCountLabel, Label individualWordCountLabel, Button quitButton) {
        this.numOfFilesLabel = numOfFilesLabel;
        this.characterCountLabel = characterCountLabel;
        this.lineCountLabel = lineCountLabel;
        this.wordCountLabel = individualWordCountLabel;
        this.quitButton = quitButton;
    }

    public static OutputWindowComponents createOutputWindowComponents(){
        Label numOfFilesLabel = new Label("Number of files: " + fileStats.numberOfFiles);
        numOfFilesLabel.setFont(new Font("Arial", 20));
        numOfFilesLabel.setAlignment(Pos.CENTER);

        Label characterCountLabel = new Label("Total character count: " + fileStats.characterCount);
        characterCountLabel.setFont(new Font("Arial", 20));
        characterCountLabel.setAlignment(Pos.CENTER);

        Label lineCountLabel = new Label("Total line count: " + fileStats.lineCount);
        lineCountLabel.setFont(new Font("Arial", 20));
        lineCountLabel.setAlignment(Pos.CENTER);

        Label wordCountLabel = new Label("Total word count: " + fileStats.wordCount.size());
        wordCountLabel.setFont(new Font("Arial", 20));
        wordCountLabel.setAlignment(Pos.CENTER);

        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        quitButton.setFont(new Font("Arial", 18));

        return new OutputWindowComponents(numOfFilesLabel, characterCountLabel, lineCountLabel, wordCountLabel, quitButton);
    }
}
