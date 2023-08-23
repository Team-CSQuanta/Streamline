package com.csquanta.streamline.Networking;

import javafx.application.Platform;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;

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
                if(!(receivedMessage.isAccepted())) {
                    String pomodoroSession = receivedMessage.getChallengeTaskPomodoroSession();
                    String receiverEmail = receivedMessage.getReceiverEmail();
                    String challengeType = receivedMessage.getChallengeType();
                    String challengeDescription = receivedMessage.getChallengeDescription();
                    String taskTag = receivedMessage.getChallengeTaskTag();
                    String monsterName = receivedMessage.getMonstersName();
                    String taskTitle = receivedMessage.getTaskTitle();

                    System.out.println("In ReadThreadServer");
                    NetworkInformation receiverInfo = clientNetworkInformationMap.get(receiverEmail);

                    if (receiverInfo != null) {
                        if (receivedMessage.isBuildConsistency()) {
                            challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail, pomodoroSession, taskTag, monsterName, taskTitle);
                            challengeInfo.setFromServer(true);
                        } else {
                            challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail, monsterName, taskTitle);
                            challengeInfo.setFromServer(true);
                        }

                        receiverInfo.getNetworkUtil().write(challengeInfo);

                    }

                }
                else  {

                    System.out.println("Email2 " + receivedMessage.getReceiverEmail());
                    System.out.println("Email3 " + receivedMessage.getEmail());
                    informRequester(receivedMessage.getReceiverEmail(), receivedMessage.getEmail());
                }

            }
        } catch (Exception e) {
            System.out.println("Client left the server");
        }
    }

    private void informRequester(String receiverEmail, String senderClientEmail) throws IOException {
        if (receiverEmail.equals(clientEmail)) {
            String message = "Your challenge request has been accepted by " + senderClientEmail;
           networkInfo.getNetworkUtil().write(new TextMessage(message));


        }
    }
}
