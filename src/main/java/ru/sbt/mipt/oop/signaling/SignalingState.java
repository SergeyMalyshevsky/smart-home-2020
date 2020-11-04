package ru.sbt.mipt.oop.signaling;

public interface SignalingState {
    SignalingState activate(String code);
    SignalingState deactivate(String code);
    SignalingState alarm();
}
