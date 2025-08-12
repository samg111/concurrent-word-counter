package com.concurrentfileprocessor.gui.windows.components;

import java.io.File;
import java.util.List;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;

import javafx.application.Platform;
import Pos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowComponents {
    public Label welcomLabel;
    public Label inputLabel;
    public Label outputLabel;
    public Button quitButton;
    public Button inputFileButton;
    public Button outputDirectoryButton;
    public Button runButton;
    public TextField filenameField;

    public MainWindowComponents(Label welcomLabel, Label inputLabel, Label outputLabel, Button quitButton, Button inputFileButton, Button outputDirectoryButton, Button runButton, TextField filenameField) {
        this.welcomLabel = welcomLabel;
        this.inputLabel = inputLabel;
        this.outputLabel = outputLabel;
        this.quitButton = quitButton;
        this.inputFileButton = inputFileButton;
        this.outputDirectoryButton = outputDirectoryButton;
        this.runButton = runButton;
        this.filenameField = filenameField;
    }

    public static Label createWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setFont(Font.font("System", 32));
        welcomeLabel.setStyle("-fx-text-fill: #1e40af; -fx-font-weight: bold;");
        return welcomeLabel;
    }

    public static MainWindowComponents createMainWindowLabels(){
        Label welcomeLabel = new Label("File Processing Setup");
        welcomeLabel.setFont(Font.font("System", 24));
        welcomeLabel.setStyle("-fx-text-fill: #1e40af; -fx-font-weight: bold;");

        Label inputLabel = new Label("Select Input Files");
        if (inputFiles != null && !inputFiles.isEmpty()) {
            String fileNames = "";
            for (File file : inputFiles) {
                String fileName = file.getName();
                fileNames += fileName + "\n";
            }
            inputLabel.setText(fileNames);
            inputLabel.setWrapText(true);
            inputLabel.setFont(Font.font("System", 16));
            inputLabel.setStyle("-fx-text-fill: #374151;");
        } else {
            inputLabel.setFont(Font.font("System", 18));
            inputLabel.setStyle("-fx-text-fill: #6b7280;");
        }

        Label outputLabel = new Label("Select output directory and choose a file name\n(default: processed_files_stats.txt)");
        if (outputFilePath != null && !outputFilePath.equals("processed_files_stats.txt")) {
            outputLabel.setText(outputFilePath);
            outputLabel.setWrapText(true);
            outputLabel.setFont(Font.font("System", 16));
            outputLabel.setStyle("-fx-text-fill: #374151;");
        } else {
            outputLabel.setFont(Font.font("System", 18));
            outputLabel.setStyle("-fx-text-fill: #6b7280;");
        }
        outputLabel.setWrapText(true);
        outputLabel.setAlignment(Pos.CENTER);

        
        return new MainWindowComponents(welcomeLabel, inputLabel, outputLabel, null, null, null, null, null);
    }

    public static MainWindowComponents createMainWindowFields(MainWindowComponents components){
        TextField filenameField = new TextField();
        filenameField.setFont(Font.font("System", 16));
        filenameField.setText(outputFilePath);
        filenameField.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #d1d5db;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 4;" +
            "-fx-padding: 8px;"
        );
        
        components.filenameField = filenameField;

        return components;
    }

    public static MainWindowComponents createMainWindowButtons(MainWindowComponents components, Stage primaryStage){
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

        Button inputFileButton = ButtonCreator.createButton("Select Input Files", (event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Input Files");
            List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);
            if (files != null) {
                String fileNames = "";
                for (File file : files) {
                    String fileName = file.getName();
                    fileNames += fileName + "\n";
                }
                components.inputLabel.setText(fileNames);
                components.inputLabel.setWrapText(true);
                components.inputLabel.setFont(Font.font("System", 16));
                components.inputLabel.setStyle("-fx-text-fill: #374151;");
                inputFiles = files;
            }
        });
        inputFileButton.setFont(Font.font("System", 18));
        inputFileButton.setPrefSize(200, 45);
        inputFileButton.setStyle(
            "-fx-background-color: #3b82f6;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );

        Button outputDirectoryButton = ButtonCreator.createButton("Select Output Directory", (event) -> {
            String textFieldContents = components.filenameField.getText();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Output Directory");
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputFilePath = directory.getAbsolutePath() + File.separator + textFieldContents;
                components.outputLabel.setText(outputFilePath);
                components.outputLabel.setWrapText(true);
                components.outputLabel.setAlignment(Pos.CENTER);
                components.outputLabel.setFont(Font.font("System", 16));
                components.outputLabel.setStyle("-fx-text-fill: #374151;");
            }
        });
        outputDirectoryButton.setFont(Font.font("System", 18));
        outputDirectoryButton.setPrefSize(250, 45);
        outputDirectoryButton.setStyle(
            "-fx-background-color: #3b82f6;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );

        Button runButton = ButtonCreator.createButton("Process Files", (event) -> {
            // event creation in OutputWindow.java
        });
        runButton.setFont(Font.font("System", 22));
        runButton.setPrefSize(250, 55);
        runButton.setMinSize(250, 55);
        runButton.setMaxSize(250, 55);
        runButton.setStyle(
            "-fx-background-color: #059669;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;"
        );

        components.quitButton = quitButton;
        components.inputFileButton = inputFileButton;
        components.outputDirectoryButton = outputDirectoryButton;
        components.runButton = runButton;

        return components;
    }
    
}
