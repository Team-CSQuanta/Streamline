package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class dashBoardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label Userlabel;

    @FXML
    public void getUser(String user) {
        Userlabel.setText(user);
    }


}
