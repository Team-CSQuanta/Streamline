package com.csquanta.streamline.Models;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ChallengeLog implements Initializable, Serializable {
    public static ChallengeLog staticChallengeLog = new ChallengeLog();
    private HashSet<ChallengeLog> challengeLogs = new HashSet<>();
    private String userName;
    private String realName;
    private String descriptionMsg;
    private Image userAvatar;
    private String taskTitle;

    public HashSet<ChallengeLog> getChallengeLogs() {
        return challengeLogs;
    }
    public ChallengeLog(){
    }
    public ChallengeLog(String userName, String realName, String descriptionMsg, Image userAvatar, String taskTitle) {
        this.userName = userName;
        this.realName = realName;
        this.descriptionMsg = descriptionMsg;
        this.userAvatar = userAvatar;
        this.taskTitle = taskTitle;
    }

    public void setChallengeLogs(HashSet<ChallengeLog> challengeLogs) {
        this.challengeLogs = challengeLogs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescriptionMsg() {
        return descriptionMsg;
    }

    public void setDescriptionMsg(String descriptionMsg) {
        this.descriptionMsg = descriptionMsg;
    }

    public Image getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Image userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
