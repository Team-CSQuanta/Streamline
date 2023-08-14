package com.csquanta.streamline;

public enum TimeMode {
    POMODORO(2);

    private int seconds;

    TimeMode(int minutes) {
        seconds = minutes * 60;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return seconds / 60;
    }

    public void setMinutes(int minutes) {
        seconds = minutes * 60;
    }
}
