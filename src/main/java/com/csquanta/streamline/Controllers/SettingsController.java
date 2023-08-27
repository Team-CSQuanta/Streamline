package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.SidebarController.modalPaneForSignOut;
import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class SettingsController implements Initializable , Serializable {

    @FXML
    private TextField displayName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> pomodoroSessionTime;

    @FXML
    private Button saveButtonAccounts;

    @FXML
    private Button saveButtonTime;

    @FXML
    private ComboBox<String> sessionBreakTime;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField taskTagCreator;

    @FXML
    void editDisplayName(MouseEvent event) {
        displayName.setEditable(true);
    }

    @FXML
    void editEmail(MouseEvent event) {
        email.setEditable(true);
    }

    @FXML
    void editPassword(MouseEvent event) {
        password.setEditable(true);
    }

    @FXML
    void editPomodoroSessionTime(MouseEvent event) {
        pomodoroSessionTime.setDisable(false);
    }

    @FXML
    void editSessionBreakTime(MouseEvent event) {
        sessionBreakTime.setDisable(false);
    }

    @FXML
    void editUserName(MouseEvent event) {
        userName.setEditable(true);
    }


    @FXML
    void OnMiscellaneousSaveClicked(ActionEvent event) {

    }
    @FXML
    void saveAccountsInfo(MouseEvent event) {
        userInfo.setUserName(userName.getText());
        userInfo.setDisplayName(displayName.getText());
        userInfo.setEmail(email.getText());
        userInfo.setPassword(password.getText());


        displayName.setText(userInfo.getDisplayName());
        userName.setText(userInfo.getUserName());
        email.setText(userInfo.getEmail());
        password.setText(userInfo.getPassword());

        modalPaneForSignOut.hide(true);
    }

    @FXML
    void saveTimeInfo(MouseEvent event) {
        if(saveButtonTime.getText().equals("Edit")){
            sessionBreakTime.setDisable(true);
            pomodoroSessionTime.setDisable(true);
        }else{
            System.out.println("Saved");
        }
        userInfo.setPomodoroSessionTime(pomodoroSessionTime.getValue());
        userInfo.setBreakTime(sessionBreakTime.getValue());
        pomodoroSessionTime.setValue(userInfo.getPomodoroSessionTime());
        sessionBreakTime.setValue(userInfo.getBreakTime());
        modalPaneForSignOut.hide(true);
    }
    ObservableList<String> sessionTime = FXCollections.observableArrayList("1","25", "50", "75", "100", "125");
    ObservableList<String> breakTime = FXCollections.observableArrayList("1", "5", "10", "15", "30");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessionBreakTime.setItems(breakTime);
        pomodoroSessionTime.setItems(sessionTime);

        displayName.setText(userInfo.getDisplayName());
        userName.setText(userInfo.getUserName());
        email.setText(userInfo.getEmail());
        password.setText(userInfo.getPassword());
        pomodoroSessionTime.setValue(userInfo.getPomodoroSessionTime());
        sessionBreakTime.setValue(userInfo.getBreakTime());


    }
}
