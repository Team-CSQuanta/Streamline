package com.csquanta.streamline.Networking;

import java.util.concurrent.ConcurrentHashMap;

public class ReadThreadServer extends Thread {

    private String clientEmail;
    private NetworkInformation networkInfo;
    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ReadThreadServer(String ClientEmail, NetworkInformation networkInfo, ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap) {
        this.clientEmail = ClientEmail;
        this.networkInfo = networkInfo;
        this.clientNetworkInformationMap = clientNetworkInformationMap;
    }

    @Override
    public void run() {

        while (true) {
            try {

                Message receivedMessage = (Message) networkInfo.getNetworkUtil().read();
                String sender = receivedMessage.getFrom();
                String receiver = receivedMessage.getTo();
                NetworkInformation receiverInfo = clientNetworkInformationMap.get(receiver);

                if (receivedMessage.getMessageType() == MessageType.CHALLENGE) {
                    String pomodoroSession = ((ChallengeMessage) receivedMessage).getChallengeTaskPomodoroSession();
                    String challengeType = ((ChallengeMessage) receivedMessage).getChallengeType();
                    String challengeDescription = ((ChallengeMessage) receivedMessage).getChallengeDescription();
                    String taskTag = ((ChallengeMessage) receivedMessage).getChallengeTaskTag();
                    String monsterName = ((ChallengeMessage) receivedMessage).getMonstersName();
                    String taskTitle = ((ChallengeMessage) receivedMessage).getTaskTitle();

                   // String challengeSenderName = ((ChallengeMessage) receivedMessage).getChallengeRequestSenderName();

                    byte[]  imageData= ((ChallengeMessage) receivedMessage).getImageData();



                    ChallengeMessage challengeMessage;

//                     if (receiverInfo != null) {
//                         if (((ChallengeMessage) receivedMessage).isBuildConsistency()) {

//                             challengeMessage = new ChallengeMessage(challengeSenderName, challengeType, challengeDescription, sender, receiver, pomodoroSession, taskTag, monsterName, taskTitle);

//                         //} else {
//                            // challengeMessage = new ChallengeMessage(challengeSenderName, challengeType, challengeDescription, sender, receiver, monsterName, taskTitle);

//                             challengeMessage = new ChallengeMessage(challengeType, challengeDescription, sender, receiver, pomodoroSession, taskTag, monsterName, taskTitle,imageData);

//                         } else {
//                             challengeMessage = new ChallengeMessage(challengeType, challengeDescription, sender, receiver, monsterName, taskTitle,imageData);


//                         }
//                         receiverInfo.getNetworkUtil().write(challengeMessage);

//                     }

//                 } 
                  
                  if (receiverInfo != null) {
                        if (((ChallengeMessage) receivedMessage).isBuildConsistency()) {
                            challengeMessage = new ChallengeMessage(challengeType, challengeDescription, sender, receiver, pomodoroSession, taskTag, monsterName, taskTitle,imageData);

                        } else {
                            challengeMessage = new ChallengeMessage(challengeType, challengeDescription, sender, receiver, monsterName, taskTitle,imageData);

                        }
                        receiverInfo.getNetworkUtil().write(challengeMessage);

                    }

                }
                  else if (receivedMessage.getMessageType() == MessageType.CHALLENGE_RESPONSE) {

                    String response = ((ChallengeResponse) receivedMessage).getResponseMessage();
                    ChallengeResponse responseMessage = new ChallengeResponse(sender, receiver, response);
                    receiverInfo.getNetworkUtil().write(responseMessage);

                } else if (receivedMessage.getMessageType() == MessageType.CHALLENGE_UPDATE) {

                    String title = ((ChallengeUpdate) receivedMessage).getTitle();
                    ChallengeUpdate challengeUpdate = new ChallengeUpdate(sender, receiver, title);
                    receiverInfo.getNetworkUtil().write(challengeUpdate);



                        }
                   
                }catch (Exception e){
                    System.out.println("Exception occurred in read thread server");
                    return;
                }

            }

        }

    }

