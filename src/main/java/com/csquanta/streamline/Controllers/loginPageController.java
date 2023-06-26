package com.csquanta.streamline.Controllers;

import java.io.IOException;
import java.net.URL;

import com.csquanta.streamline.Models.loginPageModel;
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
            isConnected.setText("Connection is successful!");
        } else {

            showAlert(Alert.AlertType.ERROR, "Database connection", "Database connection is not successful!");
        }

    }

    public void Login(ActionEvent event) throws SQLException, IOException {
        try {
            if (loginModel.isLogin(userName.getText(), userPass.getText(), userEmail.getText())) {
                isConnected.setText("User name,password and email is correct");
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Fxml/dashBoard.fxml")).openStream()));
                Stage primaryStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                DashBoardController controller = loader.getController();
                controller.getUser(userName.getText());
                primaryStage.setTitle("User DashBoard");
                Scene scene = new Scene(root, 1500, 1000);
                // setting global stylesheet
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                primaryStage.show();


            } else {
                showAlert(Alert.AlertType.ERROR, "Wrong entry", "Provided information is not in the database!");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Wrong entry", "Provided information is not in the database!");
            e.printStackTrace();
        }
    }


    public void updatePassword(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane root = FXMLLoader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Fxml/passwordUpdatingPage.fxml"))));
        Stage primaryStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        primaryStage.setTitle("Update password");
        Scene scene = new Scene(root, 1500, 1000);
        // setting global stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
        primaryStage.setScene(scene);
        // primaryStage.setFullScreen(true);
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
