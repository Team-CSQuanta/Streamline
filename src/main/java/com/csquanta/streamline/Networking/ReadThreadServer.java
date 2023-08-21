package com.csquanta.streamline.Networking;

import javafx.application.Platform;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReadThreadServer extends Thread {
    private String clientEmail;
    private NetworkInformation networkInfo;
    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ReadThreadServer(String email, NetworkInformation networkInfo, ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap) {
        this.clientEmail = email;
        this.networkInfo = networkInfo;
        this.clientNetworkInformationMap = clientNetworkInformationMap;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ChallengeInfo challengeInfo;
                ChallengeInfo receivedMessage = (ChallengeInfo) networkInfo.getNetworkUtil().read();
                String pomodoroSession = receivedMessage.getChallengeTaskPomodoroSession();
                String receiverEmail = receivedMessage.getReceiverEmail();
                String challengeType = receivedMessage.getChallengeType();
                String challengeDescription = receivedMessage.getChallengeDescription();
                String taskTag = receivedMessage.getChallengeTaskTag();
                String monsterName = receivedMessage.getMonstersName();
                String taskTitle= receivedMessage.getTaskTitle();
                System.out.println("In read thread server1");
                NetworkInformation receiverInfo = clientNetworkInformationMap.get(receiverEmail);
                if (receiverInfo != null) {
                    if (receivedMessage.isBuildConsistency()) {
                        challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag,monsterName,taskTitle);
                    }
                    else{
                        challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,monsterName,taskTitle);
                    }
                    receiverInfo.getNetworkUtil().write(challengeInfo);
                }

                if (receivedMessage.isAccepted()) {
                    System.out.println(receivedMessage.getReceiverEmail());
                    System.out.println(receivedMessage.getEmail());
                    informRequester(receivedMessage.getReceiverEmail(), receivedMessage.getEmail());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void informRequester(String acceptedClientEmail, String acceptingClientEmail) throws IOException {
        if (acceptedClientEmail.equals(clientEmail)) {
            String message = "Your challenge request has been accepted by " + acceptingClientEmail;
            networkInfo.getNetworkUtil().write(new TextMessage(message));
        }
    }
}
