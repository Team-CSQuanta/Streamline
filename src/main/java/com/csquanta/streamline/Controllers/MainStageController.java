package com.csquanta.streamline.Controllers;

import atlantafx.base.controls.ModalPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class MainStageController implements Initializable {
    public static final ModalPane modalPaneForExit = new ModalPane();
    @FXML
    private StackPane mainStageStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox header, exitOption;
        VBox tasksPage;
        try {
            header = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/Header.fxml")));
            exitOption = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ExitOption.fxml")));
            tasksPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
            StackPane.setAlignment(tasksPage, Pos.BOTTOM_CENTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainStageStackPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(KeyCode.ALT == event.getCode()){
                modalPaneForExit.setAlignment(Pos.BOTTOM_CENTER);
                modalPaneForExit.usePredefinedTransitionFactories(Side.BOTTOM);
                modalPaneForExit.show(exitOption);
            }
        });
        StackPane.setAlignment(header, Pos.TOP_CENTER);
        mainStageStackPane.getChildren().addAll(tasksPage, header, HeaderController.modalPaneForHeader,modalPaneForExit, SidebarController.modalPaneForSignOut, CreateANewTaskController.modalPaneForTaskCreator, PomodoroPageController.modalPaneForPomodoroPage);
    }
}
