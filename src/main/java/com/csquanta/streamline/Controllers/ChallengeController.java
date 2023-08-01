package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class ChallengeController implements Initializable {
    VBox noChallenge, createChallenge;
    @FXML
    private VBox ItemBlockContainer;

    @FXML
    private VBox MonsterContainerVbox;

    @FXML
    private Label MonsterLabel;

    @FXML
    private ScrollPane MonsterScrollPane;

    @FXML
    private VBox MonsterTitleVBox;

    @FXML
    private Label MonsterUnderLabeltxt;

    @FXML
    private VBox MonsterVBox;

    @FXML
    private SplitPane ShopSpiltPane;

    @FXML
    private VBox bottomVbox;

    @FXML
    private VBox shopContainerVbox;

    @FXML
    private Button takeChallengeBtn;

    @FXML
    private HBox topHbox;

    @FXML
    void takeChallenge(ActionEvent event) throws IOException {
        bottomVbox.getChildren().remove(noChallenge);
        createChallenge = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/CreateChallenge.fxml")));
        bottomVbox.getChildren().add(createChallenge);
        FadeInUp fadeInUp = new FadeInUp(createChallenge);
        fadeInUp.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            noChallenge = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/NotHavingAnyChallenge.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bottomVbox.getChildren().add(noChallenge);
    }
}
