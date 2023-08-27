package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class ChallengeUpdate extends Message implements Serializable {

    String title;


    public ChallengeUpdate( String from, String to, String title) {
        super(MessageType.CHALLENGE_UPDATE, from, to);
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
