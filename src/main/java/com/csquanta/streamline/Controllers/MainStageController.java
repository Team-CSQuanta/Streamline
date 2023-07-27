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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class MainStageController implements Initializable {
    private final ModalPane modalPaneForExit = new ModalPane();
    @FXML
    private StackPane mainStageStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox header, exitOption;
        try {
            header = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/Header.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            exitOption = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ExitOption.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainStageStackPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(KeyCode.ESCAPE == event.getCode()){
                modalPaneForExit.setAlignment(Pos.BOTTOM_CENTER);
                modalPaneForExit.usePredefinedTransitionFactories(Side.BOTTOM);
                modalPaneForExit.show(exitOption);
            }
        });
        mainStageStackPane.getChildren().addAll(HeaderController.modalPaneForHeader, header, modalPaneForExit);
    }
}
