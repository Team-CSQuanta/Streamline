package com.csquanta.streamline.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import com.csquanta.streamline.Controllers.HeaderController;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    @FXML
    private StackPane mainStageStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainStageStackPane.getChildren().addAll(HeaderController.modalPane);
    }
}
