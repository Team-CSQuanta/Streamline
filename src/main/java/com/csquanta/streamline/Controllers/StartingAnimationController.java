package com.csquanta.streamline.Controllers;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.csquanta.streamline.App.mainStage;
import static com.csquanta.streamline.App.root;
import static java.util.Objects.requireNonNull;


public class StartingAnimationController implements Initializable {
    private  double x, y;
    @FXML
    private Label logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BounceIn bounceIn = new BounceIn();
        bounceIn.setNode(logo);
        bounceIn.setDelay(Duration.seconds(.2));
        bounceIn.play();
        bounceIn.setOnFinished(event -> {
            try {
                root = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/MainStage.fxml")));

                Scene dashboard = new Scene(root);
                root.setOnMousePressed(MouseMovementEvent -> {
                    x = MouseMovementEvent.getSceneX();
                    y = MouseMovementEvent.getSceneY();
                });
                root.setOnMouseDragged(MouseMovementEvent -> {
                    mainStage.setX(MouseMovementEvent.getScreenX() - x);
                    mainStage.setY(MouseMovementEvent.getScreenY() - y);
                });




                dashboard.setFill(Color.TRANSPARENT);
                mainStage.setScene(dashboard);
                ZoomIn zoomIn = new ZoomIn();
                zoomIn.setNode(root);
                zoomIn.setSpeed(3);
                zoomIn.play();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}

