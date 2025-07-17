package com.concurrentwordcounter.gui;

import java.io.File;
import java.util.List;

import static com.concurrentwordcounter.ConcurrentWordCounter.inputFiles;
import static com.concurrentwordcounter.ConcurrentWordCounter.outputFilePath;
import com.concurrentwordcounter.gui.components.ButtonCreator;
import com.concurrentwordcounter.processor.WordCountProcessor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label welcomeLabel = createWelcomeLabel("Welcome to the Concurrent Word Counter");  
        VBox leftPane = createLeftPane(primaryStage);
        VBox rightPane = createRightPane(primaryStage);
        VBox bottomPane = createBottomPane();
        HBox centerPane = createCenterPane(leftPane, rightPane);
        BorderPane root = createRoot(welcomeLabel, centerPane, bottomPane);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setFont(new Font("Arial", 36));
        return welcomeLabel;
    }

    private VBox createLeftPane(Stage primaryStage) {
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

    private VBox createRightPane(Stage primaryStage) {
        Label outputLabel = new Label("Output File");
        Button outputFileButton = ButtonCreator.createButton("Select output directory", (event) -> {
            DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
            directoryChooser.setTitle("Select Output Directory");
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                outputFilePath = directory.getAbsolutePath();
                outputLabel.setText(outputFilePath);
                outputLabel.setWrapText(true);
                outputLabel.setFont(new Font("Arial", 16));
            }
        });
        outputFileButton.setFont(new Font("Arial", 24));
        outputLabel.setFont(new Font("Arial", 20));
        VBox rightPane = new VBox(10, outputLabel, outputFileButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setMaxWidth(Double.MAX_VALUE);
        return rightPane;
    }

    private VBox createBottomPane() {
        Button runButton = ButtonCreator.createButton("Run the Word Counter", (event) -> {WordCountProcessor.processFiles();});
        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        runButton.setFont(new Font("Arial", 28));
        quitButton.setFont(new Font("Arial", 22));
        VBox bottomPane = new VBox(20, runButton, quitButton);
        bottomPane.setAlignment(Pos.CENTER);
        return bottomPane;
    }

    private HBox createCenterPane(VBox leftPane, VBox rightPane) {
        HBox centerPane = new HBox(leftPane, rightPane);
        centerPane.setSpacing(0);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        return centerPane;
    }

    private BorderPane createRoot(Label welcomeLabel, HBox centerPane, VBox bottomPane) {
        BorderPane root = new BorderPane();
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);
        return root;
    }
}
