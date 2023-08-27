package com.csquanta.streamline.Networking;

import java.io.Serializable;

public class MrMonsterWinner extends Message implements Serializable {

    private boolean signal ;
    private String attackType;
    public MrMonsterWinner( String from, String to,boolean signal, String attackType) {
        super(MessageType.MONSTER_ATTACK, from, to);
        this.signal = signal;
        this.attackType = attackType;

    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
}
