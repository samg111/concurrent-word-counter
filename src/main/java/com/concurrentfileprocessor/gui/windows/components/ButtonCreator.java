package com.concurrentfileprocessor.gui.windows.components;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ButtonCreator {
    public static Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setOnAction(eventHandler);
        return button;
    }

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
}
