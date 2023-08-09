package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeOut;
import animatefx.animation.Pulse;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.Styles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class SignOutWarningPageController implements Initializable {

    @FXML
    private Button noBtn;

    @FXML
    private Button yesBtn;

    @FXML
    void noBtnClicked(ActionEvent event) {
        SidebarController.modalPaneForSignOut.hide(true);
        FadeOut pulse = new FadeOut();
        pulse.setNode(SidebarController.modalPaneForSignOut);
        pulse.setSpeed(2);
        pulse.play();
    }

    @FXML
    void yesBtnClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yesBtn.getStylesheets().add(Styles.DANGER);
        noBtn.getStylesheets().add(Styles.SUCCESS);

    }
}

