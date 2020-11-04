package ru.sbt.mipt.oop.signaling;

public class SignalingDeactivateState implements SignalingState {

    public SignalingDeactivateState() {
        System.out.println("Signaling is deactivated");
    }

    @Override
    public SignalingState activate(String code) {
        return new SignalingActivateState(code);
    }

    @Override
    public SignalingState deactivate(String code) {
        System.out.println("Signaling is already deactivated");
        return this;
    }

    @Override
    public SignalingState alarm() {
        System.out.println("Signaling is deactivated");
        return this;
    }
}
