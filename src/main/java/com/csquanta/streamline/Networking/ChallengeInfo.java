package com.csquanta.streamline.Networking;

import java.io.Serial;
import java.io.Serializable;

public class ChallengeInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 62709951824673L;
    private String challengeType;
    private String challengeDescription;
    private String challengeTaskPomodoroSession;
    private String challengeTaskTag;
    private String email;
    private String receiverEmail;
    private String monstersName;
    private  String taskTitle;
    private boolean accepted;
    String message;

    public ChallengeInfo() {

    }
    public ChallengeInfo(String message) {
        this.message = message;
    }


    public ChallengeInfo(String challengeType, String challengeDescription, String email, String receiverEmail, String monstersName, String taskTitle) {
        this.challengeType = challengeType;
        this.challengeDescription = challengeDescription;
        this.email = email;
        this.receiverEmail = receiverEmail;
        this.monstersName = monstersName;
        this.taskTitle=taskTitle;

    }


    public ChallengeInfo(String challengeType, String challengeDescription, String email, String receiverEmail,String challengeTaskPomodoroSession, String challengeTaskTag, String monstersName,String taskTitle) {
        this.challengeType = challengeType;
        this.challengeDescription = challengeDescription;
        this.challengeTaskPomodoroSession = challengeTaskPomodoroSession;
        this.challengeTaskTag = challengeTaskTag;
        this.email = email;
        this.receiverEmail = receiverEmail;
        this.monstersName = monstersName;
        this.taskTitle = taskTitle;

    }



    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }


    public String getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(String challengeType) {
        this.challengeType = challengeType;
    }

    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public String getMonstersName() {
        return monstersName;
    }

    public void setMonstersName(String monstersName) {
        this.monstersName = monstersName;
    }

    public String getChallengeTaskPomodoroSession() {
        return challengeTaskPomodoroSession;
    }

    public void setChallengeTaskPomodoroSession(String challengeTaskPomodoroSession) {
        this.challengeTaskPomodoroSession = challengeTaskPomodoroSession;
    }

    public String getChallengeTaskTag() {
        return challengeTaskTag;
    }

    public void setChallengeTaskTag(String challengeTaskTag) {
        this.challengeTaskTag = challengeTaskTag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isBuildConsistency() {
        return ("Build consistency".equals(challengeType));
    }
}