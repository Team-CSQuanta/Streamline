package com.csquanta.streamline.Controllers;

import animatefx.animation.*;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateANewTaskController implements Initializable {
    public static ModalPane modalPaneForTaskCreator = new ModalPane();
    @FXML
    private HBox mainHbox;

    @FXML
    private VBox timerContainer;

    @FXML
    void createANewTaskClicked(MouseEvent event) throws IOException {
        FXMLScene fxmlScene = FXMLScene.load("/Fxml/TaskCreator.fxml");
        VBox taskCreator = (VBox) fxmlScene.root;
        modalPaneForTaskCreator.show(taskCreator);
        Pulse pulse = new Pulse();
        pulse.setNode(modalPaneForTaskCreator);
        pulse.play();
    }

    @FXML
    void mouseEnteredIntoHBox(MouseEvent event) {
//        Bounce shake = new Bounce(this.mainHbox);
//        shake.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

