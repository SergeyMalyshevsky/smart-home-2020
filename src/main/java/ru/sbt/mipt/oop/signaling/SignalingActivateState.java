package ru.sbt.mipt.oop.signaling;

public class SignalingActivateState implements SignalingState{
    String code;

    public SignalingActivateState(String code) {
        this.code = code;
        System.out.println("Signaling is activated");
    }

    @Override
    public SignalingState activate(String code) {
        System.out.println("Signaling is already activated");
        return this;
    }

    @Override
    public SignalingState deactivate(String code) {
        if (code.equals(this.code)) {
            return new SignalingDeactivateState();
        } else {
            System.out.println("Incorrect code sent!!!");
            return this.alarm();
        }
    }

    @Override
    public SignalingState alarm() {
        return new SignalingAlarmState(code);
    }
}
