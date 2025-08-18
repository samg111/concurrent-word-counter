package com.concurrentfileprocessor.gui.windows.components;

import java.io.File;
import java.util.List;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilename;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowComponents {
    public Label welcomeLabel;
    public Label descriptionLabel;
    public Label inputLabel;
    public Label outputLabel;
    public Label outputFilenameLabel;
    public Label outputDirectoryLabel;
    public Button quitButton;
    public Button restartButton;
    public Button inputFileButton;
    public Button outputDirectoryButton;
    public Button runButton;
    public Button filenameButton;
    public TextField filenameField;

    public MainWindowComponents(Label welcomeLabel, Label descriptionLabel, Label inputLabel, Label outputLabel, Label outputFilenameLabel, Label outputDirectoryLabel, Button quitButton, Button restartButton, Button inputFileButton, Button outputDirectoryButton, Button runButton, Button filenameButton, TextField filenameField) {
        this.welcomeLabel = welcomeLabel;
        this.descriptionLabel = descriptionLabel;
        this.inputLabel = inputLabel;
        this.outputLabel = outputLabel;
        this.outputFilenameLabel = outputFilenameLabel;
        this.outputDirectoryLabel = outputDirectoryLabel;
        this.quitButton = quitButton;
        this.restartButton = restartButton;
        this.inputFileButton = inputFileButton;
        this.filenameButton = filenameButton;
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

        Label descriptionLabel = new Label("Select your input files (.txt files only) and configure output settings like changing output directory or editing filename, "+
                                           "then click the Process Files button to begin concurrent analysis");
        descriptionLabel.setFont(Font.font("System", 18));
        descriptionLabel.setAlignment(Pos.CENTER);
        descriptionLabel.setStyle("-fx-text-fill: #6b7280; -fx-text-alignment: center;");
        descriptionLabel.setWrapText(true);

        Label inputLabel = new Label("Click button below to select input files");
        if (inputFiles != null && !inputFiles.isEmpty()) {
            String fileNames = "";
            for (File file : inputFiles) {
                String fileName = file.getName();
                fileNames += fileName + "\n";
            }
            inputLabel.setText(fileNames);
        }
        inputLabel.setWrapText(true);
        inputLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label outputLabel = new Label("""
                                      Modify or keep the filename and then choose output directory
                                      (default filename: processed_files_stats.txt)
                                      (default directory: Downloads)
                                      """);
        outputLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        
        outputLabel.setWrapText(true);
        outputLabel.setAlignment(Pos.CENTER);
        outputLabel.setStyle(outputLabel.getStyle() + "; -fx-text-alignment: center;");
        outputLabel.setPadding(new javafx.geometry.Insets(0, 0, 15, 0));

        Label outputFilenameLabel = new Label("Output Filename: " + outputFilename);
        outputFilenameLabel.setFont(Font.font("System", 16));
        outputFilenameLabel.setStyle("-fx-text-fill: #374151;");

        Label outputDirectoryLabel = new Label("Output directory: " + outputFilePath);
        outputDirectoryLabel.setFont(Font.font("System", 16));
        outputDirectoryLabel.setStyle("-fx-text-fill: #374151;");

        
        return new MainWindowComponents(welcomeLabel, descriptionLabel, inputLabel, outputLabel, outputFilenameLabel, outputDirectoryLabel, null, 
                        null, null, null, null, null, null);
    }

    public static MainWindowComponents createMainWindowFields(MainWindowComponents components){
        TextField filenameField = new TextField();
        filenameField.setFont(Font.font("System", 16));
        filenameField.setText(outputFilename);
        filenameField.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #6b7280;" +
            "-fx-border-width: 2;" +
            "-fx-background-radius: 4;" +
            "-fx-padding: 8px;"
        );
        
        Platform.runLater(() -> filenameField.requestFocus());
        
        components.filenameField = filenameField;

        return components;
    }

    public static MainWindowComponents createMainWindowButtons(MainWindowComponents components, Stage primaryStage){
        // Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        // quitButton.setFont(Font.font("System", 16));
        // quitButton.setPrefSize(100, 35);
        // quitButton.setStyle(
        //     "-fx-background-color: #d1d5db;" +
        //     "-fx-text-fill: #374151;" +
        //     "-fx-border-color: #9ca3af;" +
        //     "-fx-border-width: 1;" +
        //     "-fx-background-radius: 6;"
        // );

        Button quitButton = ButtonCreator.createQuitButton();

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

        Button filenameButton = ButtonCreator.createButton("Set Filename", (event) -> {
            outputFilename = components.filenameField.getText();
            components.outputFilenameLabel.setText("Output filename: " + outputFilename);
        });

        filenameButton.setFont(Font.font("System", 14));
        filenameButton.setPrefSize(120, 45);
        filenameButton.setStyle(
            "-fx-background-color: #3b82f6;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;"
        );
        
        Button outputDirectoryButton = ButtonCreator.createButton("Select Output Directory", (event) -> {
            // String textFieldContents = components.filenameField.getText();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Output Directory");
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputFilePath = directory.getAbsolutePath();
                outputFilename = components.filenameField.getText();
                components.outputDirectoryLabel.setText("Output file path: " + outputFilePath);
                components.outputFilenameLabel.setText("Output filename: " + outputFilename);
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

        Button restartButton = ButtonCreator.createButton("Restart", (event) -> {
            // event creation in MainWindow.java
        });
        restartButton.setFont(Font.font("System", 16));
        restartButton.setPrefSize(100, 35);
        restartButton.setStyle(
            "-fx-background-color: #d1d5db;" +
            "-fx-text-fill: #374151;" +
            "-fx-border-color: #9ca3af;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 6;"
        );

        components.quitButton = quitButton;
        components.restartButton = restartButton;
        components.inputFileButton = inputFileButton;
        components.filenameButton = filenameButton;
        components.outputDirectoryButton = outputDirectoryButton;
        components.runButton = runButton;

        return components;
    }
    
}
