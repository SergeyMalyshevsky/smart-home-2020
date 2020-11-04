package ru.sbt.mipt.oop.signaling;

public class SignalingAlarmState implements SignalingState{
    String code;

    public SignalingAlarmState(String code) {
        this.code = code;
        this.alarm();
    }

    @Override
    public SignalingState activate(String code) {
        System.out.println("Signaling is alarmed.");
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
        System.out.println("Alarm!!!");
        return this;
    }
}
