package com.concurrentfileprocessor.gui.windows.components;

import java.io.File;
import java.util.List;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;

import javafx.application.Platform;
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
        welcomeLabel.setFont(new Font("Arial", 36));
        return welcomeLabel;
    }

    public static MainWindowComponents createMainWindowLabels(){
        Label welcomeLabel = new Label("welcome label");
        welcomeLabel.setFont(new Font("Arial", 20));

        Label inputLabel = new Label("Select Input Files");
        if (inputFiles != null && !inputFiles.isEmpty()) {
            String fileNames = "";
            for (File file : inputFiles) {
                String fileName = file.getName();
                fileNames += fileName + "\n";
            }
            inputLabel.setText(fileNames);
            inputLabel.setWrapText(true);
            inputLabel.setFont(new Font("Arial", 20));
        }

        Label outputLabel = new Label("Select output directory and choose a file name (default: processed_files_stats.txt)");
        if (outputFilePath != null && !outputFilePath.equals("processed_files_stats.txt")) {
            outputLabel.setText(outputFilePath);
            outputLabel.setWrapText(true);
            outputLabel.setFont(new Font("Arial", 20));
        }

        
        return new MainWindowComponents(welcomeLabel, inputLabel, outputLabel, null, null, null, null, null);
    }

    public static MainWindowComponents createMainWindowFields(MainWindowComponents components){
        TextField filenameField = new TextField();
        filenameField.setFont(new Font("Arial", 18));
        filenameField.setText(outputFilePath);
        
        components.filenameField = filenameField;

        return components;
    }

    public static MainWindowComponents createMainWindowButtons(MainWindowComponents components, Stage primaryStage){
        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        quitButton.setFont(new Font("Arial", 18));

        Button inputFileButton = ButtonCreator.createButton("Select input files", (event) -> {
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
                components.inputLabel.setFont(new Font("Arial", 16));
                inputFiles = files;
            }
        });
        inputFileButton.setFont(new Font("Arial", 24));

        Button outputDirectoryButton = ButtonCreator.createButton("Select output directory", (event) -> {
            String textFieldContents = components.filenameField.getText();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Output Directory");
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputFilePath = directory.getAbsolutePath() + File.separator + textFieldContents;
                components.outputLabel.setText(outputFilePath);
                components.outputLabel.setWrapText(true);
                components.outputLabel.setFont(new Font("Arial", 16));
            }
        });
        outputDirectoryButton.setFont(new Font("Arial", 24));

        Button runButton = ButtonCreator.createButton("Process Files", (event) -> {
            // event creation in OutputWindow.java
        });
        runButton.setFont(new Font("Arial", 28));

        components.quitButton = quitButton;
        components.inputFileButton = inputFileButton;
        components.outputDirectoryButton = outputDirectoryButton;
        components.runButton = runButton;

        return components;
    }
    
}
