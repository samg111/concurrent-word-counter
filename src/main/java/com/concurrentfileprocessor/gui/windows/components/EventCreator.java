package com.concurrentfileprocessor.gui.windows.components;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.initFileDetails;
import com.concurrentfileprocessor.gui.windows.Controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * utility class for creating event handlers
 * provides reusable event handling logic for buttons across different windows
 */
public class EventCreator {

    /**
     * adds restart event to a button that resets stats and returns to start window
     * @param button button to add restart functionality to
     * @param controller
     * @param stage
     * @return button with restart event handler attached
     */
    public static Button addRestartEvent(Button button, Controller controller, Stage stage){
        button.setOnAction(event -> {
            fileStats = fileStats.refreshFileStats();
            initFileDetails();
            controller.showStartWindow(stage);
        });
        return button;
    }
    
}
