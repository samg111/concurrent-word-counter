package com.concurrentfileprocessor.gui.windows.components;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.initFileDetails;
import com.concurrentfileprocessor.gui.windows.Controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EventCreator {

    public static Button addRestartEvent(Button button, Controller controller, Stage stage){
        button.setOnAction(event -> {
            fileStats = fileStats.refreshFileStats(fileStats);
            initFileDetails();
            controller.showStartWindow(stage);
        });
        return button;
    }
    
}
