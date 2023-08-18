package com.csquanta.streamline.Networking;

import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient extends Thread {
    private NetworkUtil networkUtil;
    private String clientEmail;

    public WriteThreadClient(NetworkUtil networkUtil, String email) {
        this.networkUtil = networkUtil;
        this.clientEmail = email;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("enter receiver Email: ");
                String receiverEmail = scanner.nextLine();

                System.out.print("Enter challenge type: ");
                String challengeType = scanner.nextLine();

                System.out.print("Enter challenge description: ");

                String challengeDescription = scanner.nextLine();

                if ("Build consistency".equals(challengeType)) {

                    System.out.print("Enter Pomodoro session: ");
                    String pomodoroSession = scanner.nextLine();

                    System.out.print("Enter challenge task tag: ");
                    String taskTag = scanner.nextLine();

                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail,  pomodoroSession, taskTag);
                    networkUtil.write(challengeInfo);

                } else {
                    ChallengeInfo challengeInfo = new ChallengeInfo(challengeType, challengeDescription, clientEmail, receiverEmail);
                    networkUtil.write(challengeInfo);
                }


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}