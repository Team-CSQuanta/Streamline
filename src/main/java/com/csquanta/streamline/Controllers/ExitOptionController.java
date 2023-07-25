package com.csquanta.streamline.Controllers;
import com.csquanta.streamline.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ExitOptionController {

    @FXML
    void closeWindow(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void minimizeWindow(MouseEvent event) {
        App.mainStage.setIconified(true);
    }

}
