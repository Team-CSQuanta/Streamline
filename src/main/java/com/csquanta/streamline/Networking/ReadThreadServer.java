package com.csquanta.streamline.Networking;

import java.util.HashMap;

public class ReadThreadServer extends Thread {
    private String clientEmail;
    private NetworkInformation networkInfo;
    private HashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ReadThreadServer(String email, NetworkInformation networkInfo, HashMap<String, NetworkInformation> clientNetworkInformationMap) {
        this.clientEmail = email;
        this.networkInfo = networkInfo;
        this.clientNetworkInformationMap = clientNetworkInformationMap;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ChallengeInfo challengeInfo;
                ChallengeInfo receivedMessage = ( ChallengeInfo)  networkInfo.getNetworkUtil().read();
                String pomodoroSession= receivedMessage.getChallengeType();
                String receiverEmail = receivedMessage.getReceiverEmail();
                String challengeType = receivedMessage.getChallengeType();
                String challengeDescription = receivedMessage.getChallengeDescription();
                String taskTag = receivedMessage.getChallengeTaskTag();

                NetworkInformation receiverInfo = clientNetworkInformationMap.get(receiverEmail);
                if (receiverInfo != null) {
                    if (receivedMessage.isBuildConsistency()) {
                        challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag);
                    }
                    else{
                         challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail);
                    }
                    receiverInfo.getNetworkUtil().write(challengeInfo);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}