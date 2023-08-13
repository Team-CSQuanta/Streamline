package com.csquanta.streamline;

public interface CountDownObserver {
    void update(int seconds);
    void timeIsUp();
}
