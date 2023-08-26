package com.csquanta.streamline.Networking;

import java.io.File;

public class ChallengeParticipantsInfo {
    public static ChallengeParticipantsInfo challengeParticipantsInfo = new ChallengeParticipantsInfo();
    private String participantsEmail;
    private String participantsName;

    private File participantsImageFile;
    public String getParticipantsEmail() {
        return participantsEmail;
    }

    public void setParticipantsEmail(String participantsEmail) {
        this.participantsEmail = participantsEmail;
    }

    public String getParticipantsName() {
        return participantsName;
    }

    public void setParticipantsName(String participantsName) {
        this.participantsName = participantsName;
    }

    public File getParticipantsImageFile() {
        return participantsImageFile;
    }

    public void setParticipantsImageFile(File participantsImageFile) {
        this.participantsImageFile = participantsImageFile;
    }
}
