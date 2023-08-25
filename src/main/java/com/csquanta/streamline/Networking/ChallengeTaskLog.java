package com.csquanta.streamline.Networking;

import atlantafx.base.controls.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class ChallengeTaskLog implements Serializable {
    public static ChallengeTaskLog taskLog = new ChallengeTaskLog();
    private ArrayList<ChallengeTaskLog> challengeTaskLogs = new ArrayList<>();
    private String userDisplayName;
    private String userEmail;
    private String taskTitle;

    public ChallengeTaskLog() {
    }

    public ChallengeTaskLog(String userDisplayName, String userEmail, String taskTitle) {
        this.userDisplayName = userDisplayName;
        this.userEmail = userEmail;
        this.taskTitle = taskTitle;
    }

    public ArrayList<ChallengeTaskLog> getChallengeTaskLogs() {
        return challengeTaskLogs;
    }

    public void setChallengeTaskLogs(ArrayList<ChallengeTaskLog> challengeTaskLogs) {
        this.challengeTaskLogs = challengeTaskLogs;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

}
