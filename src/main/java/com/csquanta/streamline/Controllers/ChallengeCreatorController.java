package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.UserInformation;
import com.csquanta.streamline.Networking.ChallengeMessage;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ChallengeCreatorController implements Initializable {

    ObservableList<String> challenges = FXCollections.observableArrayList("Complete daily tasks", "Build consistency");
    ObservableList<String> monster = FXCollections.observableArrayList("Amber", "Armadillo", "Axolotl", "Badger", "Butterfly", "Cheetah", "Derby", "Dilatory",
    "Dilatory Distress");
    ObservableList<String> PomodoroSession= FXCollections.observableArrayList("1", "2","3","4","5","6","7");
    ObservableList<String> taskTag = FXCollections.observableArrayList("Programming","Study","Academic studies");
    @FXML
    private Button cancel;

    @FXML
    private TextArea challengeDescription;
    @FXML
    private TextField TaskTitle;


    @FXML
    private ComboBox<String> challengeTaskPomodoroSession;

    @FXML
    private ComboBox<String> challengeTaskTag;

    @FXML
    private ComboBox<String> challengeType;
    @FXML
    private ComboBox<String> selectMonster;

    @FXML
    private TextField email;

    @FXML
    private Button sendReq;


    @FXML
    private HBox dailyTaskNecessaryField;
    ChallengeController controller;

     ChallengeController challengeController= new ChallengeController();

//    ChallengeLogController chatBoxController = new ChallengeLogController();
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

    public ObservableList<String> getChallenges() {
        return challenges;
    }

    public void setChallenges(ObservableList<String> challenges) {
        this.challenges = challenges;
    }

    public TextArea getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(TextArea challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public TextField getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(TextField taskTitle) {
        TaskTitle = taskTitle;
    }

    public ComboBox<String> getChallengeTaskPomodoroSession() {
        return challengeTaskPomodoroSession;
    }

    public void setChallengeTaskPomodoroSession(ComboBox<String> challengeTaskPomodoroSession) {
        this.challengeTaskPomodoroSession = challengeTaskPomodoroSession;
    }

    public ComboBox<String> getChallengeTaskTag() {
        return challengeTaskTag;
    }

    public void setChallengeTaskTag(ComboBox<String> challengeTaskTag) {
        this.challengeTaskTag = challengeTaskTag;
    }

    public ComboBox<String> getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ComboBox<String> challengeType) {
        this.challengeType = challengeType;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public ComboBox<String> getSelectMonster() {
        return selectMonster;
    }

    public void setSelectMonster(ComboBox<String> selectMonster) {
        this.selectMonster = selectMonster;
    }

    @FXML
    void onCancelButtonClicked(ActionEvent event) {
        modalPaneForHeader.hide(true);
    }




    @FXML
    void onSendReqClicked(ActionEvent event) {


        try {
            String receiverEmail = email.getText();
            String challengeType = String.valueOf(getChallengeType().getValue());
            String challengeDescription = String.valueOf(getChallengeDescription().getText());
            String pomodoroSession = String.valueOf(getChallengeTaskPomodoroSession().getValue());
            String taskTag = String.valueOf(getChallengeTaskTag().getValue());
            String monstersName = String.valueOf(getSelectMonster().getValue());
            String taskTitle = TaskTitle.getText();


            if ("Build consistency".equals(challengeType)) {
                ChallengeMessage challengeMessage = new ChallengeMessage( challengeType,challengeDescription,challengeController.loadClientInfoFromFile(), receiverEmail, pomodoroSession, taskTag, monstersName,taskTitle);

                networkUtil.write(challengeMessage);

            } else {
                ChallengeMessage challengeMessage= new ChallengeMessage( challengeType, challengeDescription, challengeController.loadClientInfoFromFile(), receiverEmail,monstersName,taskTitle);
                networkUtil.write(challengeMessage);

            }

            FXMLScene challengeReqSent = FXMLScene.load("/Fxml/ChallengeRequestSent.fxml");
            ChallengeUI.challengeUI.setPendingStatus(true);
            App.newLoad();
            FXMLScene challengePage = FXMLScene.load("/Fxml/Challenge.fxml");
            controller = (ChallengeController) challengePage.controller;
            controller.getBottomVbox().getChildren().setAll(challengeReqSent.root);
            StackPane.setAlignment(challengePage.root, Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengePage.root);
            FadeIn fadeIn = new FadeIn(challengePage.root);
            fadeIn.play();
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> ChangeChallengePageUI());
            pause.play();
           // Platform.runLater(() -> chatBoxController.addChatMessage(challengeInfo));
        } catch (Exception e) {
            System.out.println(e);
        }
        modalPaneForHeader.hide(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        challengeType.setItems(challenges);
        selectMonster.setItems(monster);
        challengeTaskPomodoroSession.setItems(PomodoroSession);
        challengeTaskTag.setItems(taskTag);
        dailyTaskNecessaryField.setVisible(false);


    }
public void ChangeChallengePageUI(){

//        try {
//            FXMLScene  chatBox = FXMLScene.load("/Fxml/ChatBox.fxml");
//            ChatBoxController chatBoxController = (ChatBoxController) chatBox.controller;
//            controller.getBottomVbox().getChildren().setAll(chatBox.root);
//            System.out.println("creatorControl " +chatBoxController);
//
//            ZoomIn zoomIn = new ZoomIn();
//            zoomIn.setNode(chatBox.root);
//            zoomIn.setSpeed(3);
//            zoomIn.play();

//            FXMLScene ChallengedMonster = FXMLScene.load("/Fxml/MonsterInChallenge.fxml");
//            controller.getTopHbox().getChildren().setAll(ChallengedMonster.root);
//            zoomIn.setNode(ChallengedMonster.root);
//            zoomIn.setSpeed(3);
//            zoomIn.play();

//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
}

}
