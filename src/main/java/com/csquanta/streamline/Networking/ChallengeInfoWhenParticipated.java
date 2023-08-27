package com.csquanta.streamline.Networking;

import java.io.File;

public class ChallengeInfoWhenParticipated {
    public static ChallengeInfoWhenParticipated challengeInfoWhenParticipated = new ChallengeInfoWhenParticipated();
    private String participantsEmail;
    private String participantsName;

    private File participantsImageFile;
    private String selectedMonsterName;

    public String getSelectedMonsterName() {
        return selectedMonsterName;
    }

    public void setSelectedMonsterName(String selectedMonsterName) {
        this.selectedMonsterName = selectedMonsterName;
    }

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
