package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class ChallengeMessage extends Message implements Serializable {
    private String challengeRequestSenderName;
    private String challengeType;
    private String challengeDescription;
    private String challengeTaskPomodoroSession;
    private String challengeTaskTag;
    private String from;
    private String to;
    private String monstersName;
    private  String taskTitle;
    private byte[] imageData;




   // public ChallengeMessage(String challengeRequestSenderName,String challengeType, String challengeDescription, String from, String to, String monstersName, String taskTitle)

    public ChallengeMessage( String challengeType, String challengeDescription, String from, String to, String monstersName, String taskTitle,byte[] imageData)

    {
        super(MessageType.CHALLENGE, from, to);
        this.challengeType = challengeType;
        this.challengeDescription = challengeDescription;
        this.from = from;
        this.to = to;
        this.monstersName = monstersName;
        this.taskTitle=taskTitle;
        this.imageData = imageData;

       // this.challengeRequestSenderName = challengeRequestSenderName;

    }

   // public ChallengeMessage(String challengeRequestSenderName, String challengeType, String challengeDescription, String from, String to,String challengeTaskPomodoroSession, String challengeTaskTag, String monstersName,String taskTitle) {

      

   // }

    public ChallengeMessage( String challengeType, String challengeDescription, String from, String to,String challengeTaskPomodoroSession, String challengeTaskTag, String monstersName,String taskTitle,byte[] imageData) {

        super(MessageType.CHALLENGE, from, to);
        this.challengeType = challengeType;
        this.challengeDescription = challengeDescription;
        this.challengeTaskPomodoroSession = challengeTaskPomodoroSession;
        this.challengeTaskTag = challengeTaskTag;
        this.from = from;
        this.to = to;
        this.monstersName = monstersName;
        this.taskTitle = taskTitle;

        //this.challengeRequestSenderName = challengeRequestSenderName;

       // this.imageData = imageData;


    }

    public String getChallengeRequestSenderName() {
        return challengeRequestSenderName;
    }

    public void setChallengeRequestSenderName(String challengeRequestSenderName) {
        this.challengeRequestSenderName = challengeRequestSenderName;
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

    @Override
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMonstersName() {
        return monstersName;
    }

    public void setMonstersName(String monstersName) {
        this.monstersName = monstersName;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }


    public byte[] getImageData() {
        return imageData;
    }


    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isBuildConsistency() {
        return ("Build consistency".equals(challengeType));
    }
}
