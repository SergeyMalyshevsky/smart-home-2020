package ru.sbt.mipt.oop.signaling;

public class Signaling {
    SignalingState state;
    String code = "123456";

    public Signaling() {
        this.state = new SignalingDeactivateState();
    }

    public void activate() {
        this.state = state.activate(this.code);
    }

    public void deactivate(String code) {
        this.state = state.deactivate(code);
    }

    public void alarm() {
        this.state = state.alarm();
    }

    public void stopAlarm() {
        this.state = state.deactivate(this.code);
    }
}
