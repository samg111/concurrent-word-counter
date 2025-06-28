package com.concurrentwordcounter.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("welcome to the Concurrent Word Counter");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
