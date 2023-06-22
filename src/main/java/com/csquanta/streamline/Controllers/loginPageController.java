package com.csquanta.streamline.Controllers;

import java.net.URL;
import com.csquanta.streamline.Models.loginPageModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class loginPageController implements Initializable {
    public loginPageModel loginModel = new loginPageModel();
    @FXML
    private Label isConnected;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginwithGoogle;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPass;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {

            isConnected.setText("Not Connected");
        }
    }
public void Login(ActionEvent event) throws SQLException {
        try {
            if (loginModel.isLogin(userName.getText(), userPass.getText(),userEmail.getText())){
           isConnected.setText("User name,password and email is correct");
        }  else {
                isConnected.setText("User name or password or email is not correct");
            }
        } catch (SQLException e){
            isConnected.setText("User name or password or email is not correct");
            e.printStackTrace();
        }
}
}
