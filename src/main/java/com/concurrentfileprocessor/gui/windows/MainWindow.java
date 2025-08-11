package com.concurrentfileprocessor.gui.windows;

import com.concurrentfileprocessor.gui.windows.components.MainWindowComponents;
import com.concurrentfileprocessor.processor.FileProcessor;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
    private final Controller controller;
    private final Stage stage;

    public MainWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void show() {
        MainWindowComponents components = MainWindowComponents.createMainWindowLabels();
        components = MainWindowComponents.createMainWindowFields(components);
        components = MainWindowComponents.createMainWindowButtons(components, stage);
        components.runButton.setOnAction(event -> {
            FileProcessor.processFiles();
            controller.showOutputWindow(stage);
        });

        VBox leftPane = createLeftPane(components);
        VBox rightPane = createRightPane(components, stage);
        VBox bottomPane = createBottomPane(components);
        HBox centerPane = createCenterPane(leftPane, rightPane);
        BorderPane root = createRoot(components.welcomLabel, centerPane, bottomPane);
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

    public static VBox createLeftPane(MainWindowComponents components) {
        VBox leftPane = new VBox(10, components.inputLabel, components.inputFileButton);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        return leftPane;
    }

    public static VBox createRightPane(MainWindowComponents components, Stage primaryStage) {
        VBox rightPane = new VBox(10, components.outputLabel, components.filenameField, components.outputDirectoryButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setMaxWidth(Double.MAX_VALUE);
        return rightPane;
    }

    public VBox createBottomPane(MainWindowComponents components) {
        VBox bottomPane = new VBox(20, components.runButton, components.quitButton);
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

