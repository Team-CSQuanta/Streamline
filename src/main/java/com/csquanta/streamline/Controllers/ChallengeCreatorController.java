package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Networking.ChallengeInfoWhenParticipated;
import com.csquanta.streamline.Networking.ChallengeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;
import static com.csquanta.streamline.Controllers.TaskCreatorController.tags;
import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class ChallengeCreatorController implements Initializable {

    ObservableList<String> challenges = FXCollections.observableArrayList("Complete daily tasks", "Build consistency");
    ObservableList<String> monster = FXCollections.observableArrayList("Amber", "Armadillo", "Axolotl", "Badger", "Butterfly", "Cheetah", "Derby", "Dilatory",
    "Dilatory Distress");
    ObservableList<String> PomodoroSession= FXCollections.observableArrayList("1", "2","3","4","5","6","7");

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



    @FXML
    void challengeSelection(ActionEvent event) {
        if(challengeType.getSelectionModel().getSelectedItem().equals("Build consistency")){
            dailyTaskNecessaryField.setVisible(true);
            TaskTitle.setVisible(true);
            new FadeIn(dailyTaskNecessaryField).play();
        }
        else{
            dailyTaskNecessaryField.setVisible(false);
            TaskTitle.setVisible(false);
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

        String receiverEmail = null;
        try {
            String imagePath = "ProfileImage.png";
            receiverEmail = email.getText();
            String challengeType = String.valueOf(getChallengeType().getValue());
            String challengeDescription = String.valueOf(getChallengeDescription().getText());
            String pomodoroSession = String.valueOf(getChallengeTaskPomodoroSession().getValue());
            String taskTag = String.valueOf(getChallengeTaskTag().getValue());
            String monstersName = getSelectMonster().getSelectionModel().getSelectedItem();
            String taskTitle = TaskTitle.getText();
            ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setMonsterNameForChallengeSender(selectMonster.getSelectionModel().getSelectedItem());
            ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setChallengeSenderStatus(true);
            if ("Build consistency".equals(challengeType)) {

                File imageFile = new File(imagePath);
                byte[] imageBytes = new byte[(int) imageFile.length()];
                try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                    int bytesRead = fileInputStream.read(imageBytes);
                    if (bytesRead != -1) {
                        ChallengeMessage challengeMessage = new ChallengeMessage(userInfo.getDisplayName(), challengeType,challengeDescription,userInfo.getEmail(), receiverEmail, pomodoroSession, taskTag, monstersName,taskTitle,imageBytes);

                        networkUtil.write(challengeMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }




            } else {



                File imageFile = new File(imagePath);
                byte[] imageBytes = new byte[(int) imageFile.length()];
                try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                    int bytesRead = fileInputStream.read(imageBytes);
                    if (bytesRead != -1) {
                        ChallengeMessage challengeMessage= new ChallengeMessage(userInfo.getDisplayName(), challengeType, challengeDescription,userInfo.getEmail(), receiverEmail,monstersName,taskTitle,imageBytes);
                        networkUtil.write(challengeMessage);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
            ChallengeUI.challengeUI.setPendingStatus(true);
            ChallengeUI.challengeUI.newLoadForChallengeUI();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (Objects.equals(userInfo.getEmail(), receiverEmail)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the correct email of the recipient.");
            alert.showAndWait();
            return;
        }


        modalPaneForHeader.hide(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        challengeType.setItems(challenges);
        selectMonster.setItems(monster);
        challengeTaskPomodoroSession.setItems(PomodoroSession);
        dailyTaskNecessaryField.setVisible(false);
        TaskTitle.setVisible(false);
        challengeTaskTag.setItems(tags);
    }

}
