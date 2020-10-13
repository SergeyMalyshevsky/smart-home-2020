package ru.sbt.mipt.oop.signaling;

public interface SignalingState {
    void activate(String code);
    void deactivate(String code);
    void switchToAlarmMode();

}
