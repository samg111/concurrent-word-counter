package com.concurrentfileprocessor.gui.components;

import java.io.File;
import java.util.List;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import com.concurrentfileprocessor.processor.WordCountProcessor;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PaneCreator {
    
    public static VBox createLeftPane(Stage primaryStage) {
        Label inputLabel = new Label("Select Input Files");
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
                inputLabel.setText(fileNames);
                inputLabel.setWrapText(true);
                inputLabel.setFont(new Font("Arial", 16));
                inputFiles = files;
            }
        });
        inputFileButton.setFont(new Font("Arial", 24));
        inputLabel.setFont(new Font("Arial", 20));
        VBox leftPane = new VBox(10, inputLabel, inputFileButton);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        return leftPane;
    }

    public static VBox createRightPane(Stage primaryStage) {
        outputFilePath = "concurrent-word-counter-output.txt";
        Label outputLabel = new Label("Select output directory and choose a file name");
        TextField fileNameTextField = new TextField();
        Button outputDirectoryButton = ButtonCreator.createButton("Select output directory", (event) -> {
            String textFieldContents = fileNameTextField.getText();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Output Directory");
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputFilePath = directory.getAbsolutePath() + File.separator + textFieldContents;
                outputLabel.setText(outputFilePath);
                outputLabel.setWrapText(true);
                outputLabel.setFont(new Font("Arial", 16));
            }
        });
        outputDirectoryButton.setFont(new Font("Arial", 24));
        outputLabel.setFont(new Font("Arial", 20));
        fileNameTextField.setFont(new Font("Arial", 18));
        fileNameTextField.setText(outputFilePath);
        VBox rightPane = new VBox(10, outputLabel, fileNameTextField, outputDirectoryButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setMaxWidth(Double.MAX_VALUE);
        return rightPane;
    }

    public static Label createWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setFont(new Font("Arial", 36));
        return welcomeLabel;
    }

    public static VBox createBottomPane() {
        Button runButton = ButtonCreator.createButton("Run the Word Counter", (event) -> {WordCountProcessor.processFiles();});
        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        runButton.setFont(new Font("Arial", 28));
        quitButton.setFont(new Font("Arial", 22));
        VBox bottomPane = new VBox(20, runButton, quitButton);
        bottomPane.setAlignment(Pos.CENTER);
        return bottomPane;
    }

    public static HBox createCenterPane(VBox leftPane, VBox rightPane) {
        HBox centerPane = new HBox(leftPane, rightPane);
        centerPane.setSpacing(0);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        
        leftPane.setPrefWidth(0);
        rightPane.setPrefWidth(0);
        
        return centerPane;
    }

    public static BorderPane createRoot(Label welcomeLabel, HBox centerPane, VBox bottomPane) {
        BorderPane root = new BorderPane();
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);
        return root;
    }
}
