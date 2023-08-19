//package com.csquanta.streamline.Networking;
//
//import com.csquanta.streamline.Controllers.ChallengeCreatorController;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class WriteThreadClient extends Thread {
//    private NetworkUtil networkUtil;
//    private String clientEmail;
//    private String receiverEmail;
//    private String challengeType;
//    private String challengeDescription;
//    private String pomodoroSession;
//    private String taskTag;
//
//
//    public WriteThreadClient( String receiverEmail, String challengeType, String challengeDescription, String pomodoroSession, String taskTag) {
//        this.receiverEmail = receiverEmail;
//        this.challengeType = challengeType;
//        this.challengeDescription = challengeDescription;
//        this.pomodoroSession = pomodoroSession;
//        this.taskTag = taskTag;
//    }
//
//    public WriteThreadClient(NetworkUtil networkUtil, String email) {
//        this.networkUtil = networkUtil;
//        this.clientEmail = email;
//    }
//
//    @Override
//    public void run() {
//
//        try {
//            while (true) {
//
//
//                if ("Build consistency".equals(challengeType)) {
//
//                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag);
//                    networkUtil.write(challengeInfo);
//
//                } else {
//                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail);
//                    networkUtil.write(challengeInfo);
//                }
//
//
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}