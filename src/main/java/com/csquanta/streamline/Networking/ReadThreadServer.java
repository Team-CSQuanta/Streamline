package com.csquanta.streamline.Networking;

import javafx.application.Platform;

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

                NetworkInformation receiverInfo = clientNetworkInformationMap.get(receiverEmail);
                if (receiverInfo != null) {
                    if (receivedMessage.isBuildConsistency()) {
                        challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag,monsterName);
                    }
                    else{
                        challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,monsterName);
                    }
                    receiverInfo.getNetworkUtil().write(challengeInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
