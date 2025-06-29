package com.concurrentwordcounter.gui;

import static com.concurrentwordcounter.ConcurrentWordCounter.selectedFiles;
import com.concurrentwordcounter.gui.components.ButtonCreator;
import com.concurrentwordcounter.processor.WordCountProcessor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Welcome to the Concurrent Word Counter");
        Button runButton = ButtonCreator.createButton("Run the Word Counter", (event) -> {WordCountProcessor.processFiles();});
        Button fileButton = ButtonCreator.createButton("Select input files", (event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Input Files");
            selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
        });
        Button quitButton = ButtonCreator.createButton("Quit", (event) -> {Platform.exit();});
        label.setFont(new Font("Arial", 32));
        fileButton.setFont(new Font("Arial", 22));
        runButton.setFont(new Font("Arial", 22));
        quitButton.setFont(new Font("Arial", 22));


        // StackPane root = new StackPane(label, button);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, fileButton, runButton, quitButton);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
