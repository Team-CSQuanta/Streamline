package com.csquanta.streamline.Controllers;

import java.io.IOException;
import java.net.URL;
import com.csquanta.streamline.Models.loginPageModel;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class loginPageController implements Initializable {
    public loginPageModel loginModel = new loginPageModel();
    @FXML
    public AnchorPane container;
   @FXML
    public Button loginwithFacebook;
   @FXML
    public Button loginwithGitHub;
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

    public void Login(ActionEvent event) throws SQLException, IOException {
        try {
            if (loginModel.isLogin(userName.getText(), userPass.getText(),userEmail.getText())){
           isConnected.setText("User name,password and email is correct");
                Stage primaryStage= new Stage();
                FXMLLoader loader= new FXMLLoader();
                Pane root = loader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Fxml/dashBoard.fxml")).openStream()));
                dashBoardController controller= (dashBoardController)loader.getController();
                controller.getUser(userName.getText());
                primaryStage.setTitle("User DashBoard");
                Scene scene = new Scene(root);
                // setting global stylesheet
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();



        }  else {
                isConnected.setText("User name or password or email is not correct");
            }
        } catch (SQLException e){
            isConnected.setText("User name or password or email is not correct");
            e.printStackTrace();
        }
}


}
