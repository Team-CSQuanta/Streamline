package com.csquanta.streamline.Networking;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ReadThreadServer extends Thread {
    private String clientEmail;
    private NetworkInformation networkInfo;
    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;
    ChallengeInfo receivedMessage;
    public ReadThreadServer(String email, NetworkInformation networkInfo, ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap) {
        this.clientEmail = email;
        this.networkInfo = networkInfo;
        this.clientNetworkInformationMap = clientNetworkInformationMap;
    }

    @Override
    public void run() {

            while (true) {
                try{
                    ChallengeInfo challengeInfo;
                     receivedMessage = (ChallengeInfo) networkInfo.getNetworkUtil().read();
                    if(receivedMessage.getServerRequestCode().equals("ChallengeRequest")){
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
                                    challengeInfo = new ChallengeInfo("ChallengeRequest",challengeType, challengeDescription, clientEmail, receiverEmail, pomodoroSession, taskTag, monsterName, taskTitle);

                                } else {
                                    challengeInfo = new ChallengeInfo("ChallengeRequest",challengeType, challengeDescription, clientEmail, receiverEmail, monsterName, taskTitle);

                                }
                                receiverInfo.getNetworkUtil().write(challengeInfo);

                            }

                        }

                    }
                    else  {
                        System.out.println("Email2 " + receivedMessage.getReceiverEmail());
                        System.out.println("Email3 " + receivedMessage.getEmail());
                        informRequester(receivedMessage.getReceiverEmail(), receivedMessage.getEmail());
                    }
                }catch (Exception e){
                    System.out.println("Exception occurred in read thread server");
                }

            }

    }

    private void informRequester(String receiverEmail, String senderClientEmail) throws IOException {
        System.out.println("hello1");
        if (receiverEmail.equals(receivedMessage.getEmail())) {
            System.out.println("hello");
            String message = "Your challenge request has been accepted by " + senderClientEmail;
           networkInfo.getNetworkUtil().write(new TextMessage(message));


        }
    }
}
