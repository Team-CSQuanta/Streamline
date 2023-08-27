package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class MrMonsterWinner extends Message implements Serializable {

    int UserHealth;
    public MrMonsterWinner( String from, String to,int UserHealth) {
        super(MessageType.MONSTER_ATTACK, from, to);
        this.UserHealth = UserHealth;
    }

    public int getUserHealth() {
        return UserHealth;
    }

    public void setUserHealth(int userHealth) {
        UserHealth = userHealth;
    }
}
