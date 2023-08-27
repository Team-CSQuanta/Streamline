package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class ChallengeResponse extends Message implements Serializable{
    private  String responseMessage;
    private boolean accepted;
    private byte[] imageData;
    private String recipient_name;


    public ChallengeResponse(String recipient_name,String from, String to,String responseMessage,byte[] imageData) {
        super(MessageType.CHALLENGE_RESPONSE, from, to);
        this.responseMessage = responseMessage;
        this.imageData = imageData;
        this.recipient_name = recipient_name;

    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getResponseMessage() {return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }
}
