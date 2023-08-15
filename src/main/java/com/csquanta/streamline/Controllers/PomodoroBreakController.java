package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import com.csquanta.streamline.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PomodoroBreakController {

    @FXML
    private VBox container;

    @FXML
    private Button noBtn;

    @FXML
    private Button yesButton;

    @FXML
    void noBreak(ActionEvent event) {
        TaskBlockController.StaticTimerContainer.getChildren().clear();
        TaskBlockController.StaticTimerContainer.getChildren().add(TaskBlockController.mainContainerOfTimer);
        new FadeIn(TaskBlockController.mainContainerOfTimer);
    }

    @FXML
    void yesTakeBreak(ActionEvent event) {

    }

}
