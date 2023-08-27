package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class ChallengeResponse extends Message implements Serializable{
    private  String responseMessage;
    private boolean accepted;


    public ChallengeResponse(String from, String to,String responseMessage) {
        super(MessageType.CHALLENGE_RESPONSE, from, to);
        this.responseMessage = responseMessage;

    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


}
