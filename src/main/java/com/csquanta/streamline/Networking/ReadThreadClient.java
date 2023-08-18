package com.csquanta.streamline.Networking;

public class ReadThreadClient extends Thread {
    private NetworkUtil networkUtil;
    private String clientEmail;

    public ReadThreadClient(NetworkUtil networkUtil, String clientEmail) {
        this.networkUtil = networkUtil;
        this.clientEmail = clientEmail;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ChallengeInfo receivedMessage = ( ChallengeInfo) networkUtil.read();
                String pomodoroSession= receivedMessage.getChallengeTaskPomodoroSession();
                String challengeType = receivedMessage.getChallengeType();
                String challengeDescription = receivedMessage.getChallengeDescription();
                String taskTag = receivedMessage.getChallengeTaskTag();
                String monsterName = receivedMessage.getMonstersName();

                if ("Build consistency".equals(challengeType)) {
                    System.out.println("Received a Brand new Challenge:");
                    System.out.println(".......................................");
                    System.out.println("Challenge Type: " + challengeType);
                    System.out.println("Monster: "+ monsterName);
                    System.out.println("Description: " + challengeDescription);
                    System.out.println("Sessions: " + pomodoroSession);
                    System.out.println("Task Tag: " + taskTag);

                } else {
                    System.out.println("Received a Brand new Challenge:");
                    System.out.println(".......................................");
                    System.out.println("Challenge Type: " + challengeType);
                    System.out.println("Monster: "+ monsterName);
                    System.out.println("Description: " + challengeDescription);


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
