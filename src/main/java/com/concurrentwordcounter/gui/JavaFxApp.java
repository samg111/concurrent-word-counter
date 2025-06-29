package com.concurrentwordcounter.gui;

import java.io.File;

import static com.concurrentwordcounter.MultiThreadWordCount.selectedFiles;
import com.concurrentwordcounter.threading.ThreadDelegator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("welcome to the Concurrent Word Counter");
        Button runButton = new Button("Run the Word Counter");
        runButton.setOnAction(event -> {
            ThreadDelegator.delegateTasks();
        });
        Button fileButton = new Button("Open File Selection");
        fileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Input Files");
            selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
            if (selectedFiles != null && !selectedFiles.isEmpty()) {
                for (File file : selectedFiles) {
                    System.out.println("Selected file: " + file.getAbsolutePath());
                }
            } else {
                System.out.println("No files selected.");
            }
        });


        // StackPane root = new StackPane(label, button);

        VBox root = new VBox(10);
        root.getChildren().addAll(label, runButton, fileButton);
        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
