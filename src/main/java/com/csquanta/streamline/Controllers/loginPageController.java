package com.csquanta.streamline.Controllers;

import java.net.URL;
import com.csquanta.streamline.Models.loginPageModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.ResourceBundle;


public class loginPageController implements Initializable {
    public loginPageModel loginModel = new loginPageModel();
    @FXML
    private Label isConnected;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// TODO Auto-generated method stub
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {

            isConnected.setText("Not Connected");
        }
    }
}
