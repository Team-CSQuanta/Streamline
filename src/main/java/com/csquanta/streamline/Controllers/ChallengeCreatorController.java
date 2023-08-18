package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import com.csquanta.streamline.Networking.ChallengeInfo;
import com.csquanta.streamline.Networking.NetworkUtil;
import com.csquanta.streamline.Networking.ReadThreadClient;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ChallengeCreatorController implements Initializable {

    ObservableList<String> challenges = FXCollections.observableArrayList("Complete daily tasks", "Build consistency");
    ObservableList<String> monster = FXCollections.observableArrayList("Amber", "Armadillo", "Axolotl", "Badger", "Butterfly", "Cheetah", "Derby", "Dilatory",
    "Dilatory Distress");
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
    private ComboBox<String> selectMonster;
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

    public ComboBox<?> getChallengeTaskPomodoroSession() {
        return challengeTaskPomodoroSession;
    }

    public void setChallengeTaskPomodoroSession(ComboBox<?> challengeTaskPomodoroSession) {
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

            String clientEmail = loadClientInfoFromFile();
            NetworkUtil networkUtil = new NetworkUtil("127.0.0.1", 8000);
            networkUtil.write(clientEmail);

            ReadThreadClient readThreadClient = new ReadThreadClient(networkUtil, clientEmail);
            readThreadClient.start();

            while (true) {
                if ("Build consistency".equals(challengeType)) {
                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag);
                    networkUtil.write(challengeInfo);
                } else {
                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail);
                    networkUtil.write(challengeInfo);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        challengeType.setItems(challenges);
        selectMonster.setItems(monster);
        dailyTaskNecessaryField.setVisible(false);
    }


    private String loadClientInfoFromFile() {
        String email =null;
        try (BufferedReader reader = new BufferedReader(new FileReader("client_info.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    email = parts[1];


                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading client info file: " + e.getMessage());
        }
        return email;
    }


//    public void runClient(String serverAddress, int serverPort) {
//        try {
//            String clientEmail = loadClientInfoFromFile();
//            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
//            networkUtil.write(clientEmail);
//
//            ReadThreadClient readThreadClient = new ReadThreadClient(networkUtil, clientEmail);
//            WriteThreadClient writeThreadClient = new WriteThreadClient(networkUtil, clientEmail);
//
//            readThreadClient.start();
//            writeThreadClient.start();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

}
