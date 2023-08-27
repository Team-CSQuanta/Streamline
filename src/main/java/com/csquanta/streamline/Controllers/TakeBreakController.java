package com.csquanta.streamline.Controllers;

import atlantafx.base.controls.ToggleSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class TakeBreakController {

    @FXML
    private ToggleSwitch ToggleButton;

    @FXML
    private Label mode;
    public static boolean restMode ;



    public boolean isRestMode() {
        return restMode;
    }

    public void setRestMode(boolean restMode) {
        TakeBreakController.restMode = restMode;
    }


    @FXML
    void closeBreakPage(MouseEvent event) {
        modalPaneForHeader.hide(true);
    }

    @FXML
    void handleToggleButton(MouseEvent event) {
     if(ToggleButton.isSelected()) {
         mode.setText("You are in rest mode now!");
         setRestMode(true);
     }
     else {
         mode.setText("Turn on the rest mode if you need...");
         setRestMode(false);
     }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleButton.setSelected(UserInformation.userInfo.isRestMode());
    }
}