package com.concurrentfileprocessor.gui.windows.components;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * utility class for creating the buttons for the application
 */
public class ButtonCreator {
    /**
     * creates a basic button with custom text and event handler
     * @param text text to display on the button
     * @param eventHandler action to perform when button is clicked
     * @return configured button with specified text and action
     */
    public static Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setOnAction(eventHandler);
        return button;
    }

    /**
     * creates a quit button that exits the application
     * @return configured quit button
     */
    public static Button createQuitButton() {
        Button quitButton = createButton("Quit", (event) -> { Platform.exit();});
        quitButton.setFont(Font.font("System", 16));
        quitButton.setPrefSize(100, 35);
        quitButton.setStyle(
            "-fx-background-color: #d1d5db;" +
            "-fx-text-fill: #374151;" +
            "-fx-border-color: #9ca3af;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 6;"
        );
        return quitButton;
    }

    /**
     * creates a restart button for resetting the application to the start window
     * event handler is set in the different Window.java classes
     * @return configured restart button
     */
    public static Button createRestartButton(){
        Button restartButton = ButtonCreator.createButton("Restart", (event) -> {
            // event creation in Window.java class
        });
        restartButton.setFont(Font.font("System", 16));
        restartButton.setPrefSize(100, 35);
        restartButton.setStyle(
            "-fx-background-color: #d1d5db;" +
            "-fx-text-fill: #374151;" +
            "-fx-border-color: #9ca3af;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 6;"
        );
        return restartButton;
    }
}
