package ru.sbt.mipt.oop.signaling;

public class Signaling implements SignalingState{
    SignalingState state;

    public Signaling(SignalingState state) {
        this.state = state;
    }

    private void changeState(SignalingState state) {
        this.state = state;
    }

    @Override
    public void activate(String code) {
        SignalingState activateState = new SignalingActivateState();
        changeState(activateState);
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        SignalingState activateState = new SignalingDeactivateState();
        changeState(activateState);
        state.deactivate(code);
    }

    @Override
    public void switchToAlarmMode() {
        SignalingState activateState = new SignalingAlarmState();
        changeState(activateState);
        state.switchToAlarmMode();
    }
}
