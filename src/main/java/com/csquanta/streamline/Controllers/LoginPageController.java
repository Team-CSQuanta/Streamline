package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.LoginPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    public LoginPageModel loginModel = new LoginPageModel();
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
            isConnected.setText("Connection is successful!");
        } else {

            showAlert(Alert.AlertType.ERROR, "Database connection", "Database connection is not successful!");
        }

    }

    public void Login(ActionEvent event) throws SQLException, IOException {
        try {
            if (loginModel.isLogin(userName.getText(), userPass.getText(), userEmail.getText())) {
                isConnected.setText("User name, password, and email are correct");
                FXMLScene scene = FXMLScene.load("/Fxml/Dashboard.fxml");
                DashBoardController controller = (DashBoardController) scene.controller;
                controller.getUser(userName.getText());
                Stage primaryStage = App.mainStage;
                primaryStage.setScene(new Scene(scene.root));
                primaryStage.setFullScreen(false);
                primaryStage.show();
            }
            else {
                showAlert(Alert.AlertType.ERROR, "Wrong entry", "Provided information is not in the database!");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Wrong entry", "Provided information is not in the database!");
            e.printStackTrace();
        }
    }


    public void updatePassword(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane root = FXMLLoader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Fxml/PasswordUpdatingPage.fxml"))));
        Stage primaryStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        primaryStage.setTitle("Update password");
        Scene scene = new Scene(root, 1500, 1000);
        // setting global stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();


    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
