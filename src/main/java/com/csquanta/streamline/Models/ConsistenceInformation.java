package com.csquanta.streamline.Models;

public class ConsistenceInformation {
    private int consistencyStreak;
    private boolean isConsistence;

    public ConsistenceInformation(int consistencyStreak, boolean isConsistence) {
        this.consistencyStreak = consistencyStreak;
        this.isConsistence = isConsistence;
    }

    public int getConsistencyStreak() {
        return consistencyStreak;
    }

    public void setConsistencyStreak(int consistencyStreak) {
        this.consistencyStreak = consistencyStreak;
    }

    public boolean isConsistence() {
        return isConsistence;
    }

    public void setConsistence(boolean consistence) {
        isConsistence = consistence;
    }
}
