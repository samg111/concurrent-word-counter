package com.concurrentfileprocessor.gui.windows;

import java.io.File;
import java.util.List;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import com.concurrentfileprocessor.gui.windows.components.ButtonCreator;
import com.concurrentfileprocessor.processor.FileProcessor;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class MainWindow {
    private final Controller controller;
    private final Stage stage;

    public MainWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void show() {
        Label welcomeLabel = createWelcomeLabel("Concurrent File Processor");
        VBox leftPane = createLeftPane(stage);
        VBox rightPane = createRightPane(stage);
        VBox bottomPane = createBottomPane(controller, stage);
        HBox centerPane = createCenterPane(leftPane, rightPane);
        BorderPane root = createRoot(welcomeLabel, centerPane, bottomPane);
        root.setStyle("-fx-background-color: #64748b;");
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Concurrent File Processor");
        stage.setScene(scene);
        
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setResizable(true);
        
        stage.centerOnScreen();
        stage.show();
        Platform.runLater(() -> stage.toFront());
    }

    public static Label createWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setFont(new Font("Arial", 36));
        return welcomeLabel;
    }

    public static VBox createLeftPane(Stage primaryStage) {
        Label inputLabel = new Label("Select Input Files");
        
        if (inputFiles != null && !inputFiles.isEmpty()) {
            String fileNames = "";
            for (File file : inputFiles) {
                String fileName = file.getName();
                fileNames += fileName + "\n";
            }
            inputLabel.setText(fileNames);
            inputLabel.setWrapText(true);
            inputLabel.setFont(new Font("Arial", 16));
        }
        
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
        Label outputLabel = new Label("Select output directory and choose a file name (default: processed_files_stats.txt)");
        TextField fileNameTextField = new TextField();
        
        if (outputFilePath != null && !outputFilePath.equals("processed_files_stats.txt")) {
            outputLabel.setText(outputFilePath);
            outputLabel.setWrapText(true);
            outputLabel.setFont(new Font("Arial", 16));
        }
        
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

    public VBox createBottomPane(Controller controller, Stage stage) {
        Button runButton = ButtonCreator.createButton("Process Files", (event) -> {
            fileStats = fileStats.refreshFileStats(fileStats);
            FileProcessor.processFiles();
            controller.showOutputWindow(stage);
        });
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

