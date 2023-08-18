package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChallengeCreatorController implements Initializable {

    ObservableList<String> challenges = FXCollections.observableArrayList("Complete daily tasks", "Build consistency");
    @FXML
    private Button cancel;

    @FXML
    private TextArea challengeDescription;

    @FXML
    private ComboBox<?> challengeTaskPomodoroSession;

    @FXML
    private ComboBox<String> challengeTaskTag;

    @FXML
    private ComboBox<String> challengeType;

    @FXML
    private TextField email;

    @FXML
    private Button sendReq;
    @FXML
    private HBox dailyTaskNecessaryField;
    @FXML
    void challengeSelection(ActionEvent event) {
        if(challengeType.getSelectionModel().getSelectedItem().equals("Build consistency")){
            dailyTaskNecessaryField.setVisible(true);
            new FadeIn(dailyTaskNecessaryField).play();
        }
        else{
            dailyTaskNecessaryField.setVisible(false);
            new FadeIn(dailyTaskNecessaryField).play();
        }
    }
    @FXML
    void onCancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void onSendReqClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        challengeType.setItems(challenges);
        dailyTaskNecessaryField.setVisible(false);
    }
}
