package com.csquanta.streamline.Controllers;

import animatefx.animation.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class StartingAnimationController implements Initializable {
    @FXML
    private Label logo;

    @FXML
    private AnchorPane rootLayout;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BounceIn bounceIn = new BounceIn();
        bounceIn.setNode(logo);
        bounceIn.setDelay(Duration.seconds(.2));
        bounceIn.play();
        bounceIn.setOnFinished(event -> {
            try{
                Stage App = (Stage)(logo.getScene()).getWindow();
                nextScene(App);
            }catch (IOException i){
                System.out.println("Exception Occurred");
            }

        });
    }
    public void nextScene(Stage App) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/loginPage.fxml")));
        Scene login = new Scene(root);
        App.setScene(login);

    }
}
