package ru.sbt.mipt.oop.signaling;

public class Signaling {
    SignalingState state;
    String code;

    public Signaling() {
        this.state = new SignalingDeactivateState();
    }

    public void activate(String code) {
        this.code = code;
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

    public boolean isActivated() {
        return state instanceof SignalingActivateState;
    }

    public boolean isDeactivated() {
        return state instanceof SignalingDeactivateState;
    }

    public boolean isAlarmed() {
        return state instanceof SignalingAlarmState;
    }
}
