package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class Message implements Serializable {
    private MessageType messageType;
    private String from;
    private String to;

    public Message(MessageType messageType, String from, String to) {
        this.messageType = messageType;
        this.from = from;
        this.to = to;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}


