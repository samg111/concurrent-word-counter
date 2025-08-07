package com.concurrentfileprocessor.gui.windows;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OutputWindow {
    private Controller controller;
    private Stage stage;

    public OutputWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }
    
    public void show() {
        Platform.runLater(() -> {
            Stage resultsStage = new Stage();
            resultsStage.setTitle("Results Window");
            resultsStage.setWidth(400);
            resultsStage.setHeight(300);
            
            VBox root = new VBox(20);
            root.setAlignment(Pos.CENTER);
            root.setStyle("-fx-background-color: #64748b;");
            
            Label label = new Label("Processing Complete");
            label.setFont(new Font("Arial", 18));
            
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> resultsStage.close());
            
            root.getChildren().addAll(label, closeButton);
            
            Scene scene = new Scene(root);
            resultsStage.setScene(scene);
            resultsStage.centerOnScreen();
            resultsStage.show();
        });
    }
} 

