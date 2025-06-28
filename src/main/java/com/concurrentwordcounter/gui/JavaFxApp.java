package com.concurrentwordcounter.gui;

import com.concurrentwordcounter.threading.ThreadDelegator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("welcome to the Concurrent Word Counter");
        Button button = new Button("Run the Word Counter");
        button.setOnAction(event -> {
            ThreadDelegator.delegateTasks();
        });


        // StackPane root = new StackPane(label, button);

        VBox root = new VBox(10);
        root.getChildren().addAll(label, button);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Concurrent Word Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
