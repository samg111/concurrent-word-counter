package com.concurrentfileprocessor.gui.windows;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.initFileDetails;
import com.concurrentfileprocessor.gui.windows.components.OutputWindowComponents;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OutputWindow {
    private final Controller controller;
    private final Stage stage;

    public OutputWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }
    
    public void show() {
        OutputWindowComponents components = OutputWindowComponents.createOutputWindowLabels();
        components = OutputWindowComponents.createOutputWindowButtons(components);

        components.backMainButton.setOnAction(event -> {
            fileStats = fileStats.refreshFileStats(fileStats);
            controller.showMainWindow(stage);
        });
        components.backStartButton.setOnAction(event -> {
            fileStats = fileStats.refreshFileStats(fileStats);
            initFileDetails();
            controller.showStartWindow(stage);
        });

        VBox fileMetricsBox = new VBox(20);
        fileMetricsBox.setAlignment(Pos.CENTER);
        fileMetricsBox.getChildren().addAll(components.numOfFilesLabel, components.lineCountLabel, components.characterCountLabel, components.wordCountLabel);

        VBox navigationBox = new VBox(10);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(components.backMainButton, components.backStartButton, components.quitButton);
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #64748b;");
        root.setCenter(fileMetricsBox);
        root.setBottom(navigationBox);

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Output Results Window");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static Label createOutputLabels(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setFont(new Font("Arial", 36));
        return welcomeLabel;
    }
} 

