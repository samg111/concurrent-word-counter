package com.concurrentfileprocessor.gui.windows;

import com.concurrentfileprocessor.gui.windows.components.EventCreator;
import com.concurrentfileprocessor.gui.windows.components.MainWindowComponents;
import com.concurrentfileprocessor.processor.FileProcessor;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * display main processing window for file selection and configuration
 * allows users to select input files, configure output settings and start processing
 */
public class MainWindow {
    private final Controller controller;
    private final Stage stage;

    /**
     * constructor
     * @param controller controller for window navigation
     * @param stage stage to display window
     */
    public MainWindow(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    /**
     * displays the main processing window
     * sets up file selection and output configuration interface and process button
     */
    public void show() {
        // create and configure UI components
        MainWindowComponents components = MainWindowComponents.createMainWindowLabels();
        components = MainWindowComponents.createMainWindowFields(components);
        components = MainWindowComponents.createMainWindowButtons(components, stage);
        
        // set run button actionto process files and show output window
        components.runButton.setOnAction(event -> {
            FileProcessor.processFiles();
            controller.showOutputWindow(stage);
        });
        components.restartButton = EventCreator.addRestartEvent(components.restartButton, controller, stage);
        
        // create all UI panes
        VBox leftPane = createLeftPane(components);
        VBox rightPane = createRightPane(components, stage);
        VBox bottomPane = createBottomPane(components);
        HBox centerPane = createCenterPane(leftPane, rightPane);
        
        // create top pane with title and description
        VBox topPane = new VBox(10);
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(components.windowPurposeLabel, components.descriptionLabel);
        
        // assemble final layout
        BorderPane root = createRoot(topPane, centerPane, bottomPane);
        root.setStyle("-fx-background-color: #f8fafc; -fx-padding: 20px;");
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Concurrent File Processor");
        stage.setScene(scene);
        
        // set window properties and display
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setResizable(true);
        
        stage.centerOnScreen();
        stage.show();
        Platform.runLater(() -> stage.toFront());
    }

    /**
     * creates left pane with input file selection controls
     * @param components UI components for the window
     * @return left pane
     */
    public static VBox createLeftPane(MainWindowComponents components) {
        VBox leftPane = new VBox(10, components.inputLabel, components.inputFileButton);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        return leftPane;
    }

    /**
     * creates right pane with output configuration controls
     * @param components UI components for the window
     * @param primaryStage stage for window
     * @return right pane
     */
    public static VBox createRightPane(MainWindowComponents components, Stage primaryStage) {
        // create container for filename field and button, to select output file name
        HBox filenameContainer = new HBox(5);
        filenameContainer.setAlignment(Pos.CENTER);
        filenameContainer.getChildren().addAll(components.filenameField, components.filenameButton);
        
        // configure filename field properties
        HBox.setHgrow(components.filenameField, Priority.ALWAYS);
        components.filenameField.setMaxWidth(Double.MAX_VALUE);
        
        // create right pane with output configuration controls
        VBox rightPane = new VBox(10, components.outputLabel, components.outputFilenameLabel, filenameContainer, components.outputDirectoryLabel, components.outputDirectoryButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setMaxWidth(Double.MAX_VALUE);
        return rightPane;
    }

    /**
     * creates bottom pane with navigation buttons
     * @param components UI components for the window
     * @return bottom pane
     */
    public VBox createBottomPane(MainWindowComponents components) {
        // create bottom pane with navigation and action buttons
        VBox bottomPane = new VBox(20, components.runButton, components.restartButton, components.quitButton);
        bottomPane.setAlignment(Pos.CENTER);
        return bottomPane;
    }

    /**
     * creates center pane combining left and right panes
     * @param leftPane left pane
     * @param rightPane right pane
     * @return center pane
     */
    public static HBox createCenterPane(VBox leftPane, VBox rightPane) {
        HBox centerPane = new HBox(leftPane, rightPane);
        centerPane.setSpacing(0);
        
        // make both panes expand to fill available space equally
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        
        // set preferred width to 0 so panes expand equally
        leftPane.setPrefWidth(0);
        rightPane.setPrefWidth(0);
        
        return centerPane;
    }

    /**
     * creates root layout combining all sections
     * @param topSection top section with title and description
     * @param centerPane center section with left and right panes   
     * @param bottomPane bottom section with navigation buttons
     * @return root layout
     */
    public static BorderPane createRoot(VBox topSection, HBox centerPane, VBox bottomPane) {
        // create root layout using BorderPane for organized sections
        BorderPane root = new BorderPane();
        root.setTop(topSection);
        BorderPane.setAlignment(topSection, Pos.CENTER);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);
        
        return root;
    }
}

