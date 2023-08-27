package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class ChallengeUpdate extends Message implements Serializable {

    private String title;
    private int numOfSession;

    public ChallengeUpdate( String from, String to, String title, int numOfNumSession) {
        super(MessageType.CHALLENGE_UPDATE, from, to);
        this.title = title;
        this.numOfSession = numOfNumSession;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfSession() {
        return numOfSession;
    }

    public void setNumOfSession(int numOfSession) {
        this.numOfSession = numOfSession;
    }
}
